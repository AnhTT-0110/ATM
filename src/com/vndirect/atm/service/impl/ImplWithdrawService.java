package com.vndirect.atm.service.impl;

import com.vndirect.atm.controller.WithdrawController;
import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.entitty.Bill;
import com.vndirect.atm.entitty.Transfer;
import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.repository.AccountRepository;
import com.vndirect.atm.repository.BillRepository;
import com.vndirect.atm.repository.TransferRepository;
import com.vndirect.atm.repository.impl.ImplAccountRepository;
import com.vndirect.atm.repository.impl.ImplBillRepository;
import com.vndirect.atm.repository.impl.ImplTransferRepository;
import com.vndirect.atm.service.WithdrawService;
import com.vndirect.atm.view.WithdrawalView;

import java.util.ArrayList;
import java.util.List;

public class ImplWithdrawService implements WithdrawService {

    private static ImplWithdrawService withdrawService = new ImplWithdrawService();
    private AccountRepository accountRepository = ImplAccountRepository.getInstance();
    private BillRepository bill = ImplBillRepository.getInstance();
    private List<Account> accountList = accountRepository.getAllAccount();
    private List<Bill> billList = bill.getAllBill();
    private TransferRepository transfer = ImplTransferRepository.getInstance();
    private LoginModel loginModel = LoginModel.getInstance();

    public static ImplWithdrawService getInstance() {
        return withdrawService;
    }

    @Override
    public List<Bill> getBill(int value) {
        int quaFifty = 0;
        int quaOne = 0;
        int quaTwo = 0;
        int quaFive = 0;
        List<Bill> billEntities = new ArrayList<>();
        billEntities.add(new Bill(50000, quaFifty));
        billEntities.add(new Bill(100000, quaOne));
        billEntities.add(new Bill(200000, quaTwo));
        billEntities.add(new Bill(500000, quaFive));
        for (int i = 1; i > 0; ) {
            if (value == 0) {
                break;
            }
            if (billList.get(3).getQuantity() > 0 && value >= 500000) {
                value -= 500000;
                bill.setQuantity(3);
                setQuantityIncrease(billEntities, 3);
            } else if (billList.get(2).getQuantity() > 0 && (value < 500000) && (value >= 200000)) {
                value -= 200000;
                bill.setQuantity(2);
                setQuantityIncrease(billEntities, 2);
            } else if (billList.get(1).getQuantity() > 0 && (value < 200000) && (value >= 100000)) {
                value -= 100000;
                bill.setQuantity(1);
                setQuantityIncrease(billEntities, 1);
            } else if (billList.get(0).getQuantity() > 0 && value == 50000) {
                value -= 50000;
                bill.setQuantity(0);
                setQuantityIncrease(billEntities, 0);
            }
        }
        return billEntities;
    }

    private boolean checkAmountFifty(int amount) {
        boolean check = false;
        if (amount % 50000 == 0) {
            check = true;
        }
        return check;
    }

    private void setQuantityIncrease(List<Bill> billEntities, int index) {
        billEntities.get(index).setQuantity(billEntities.get(index).getQuantity() + 1);
    }

    public ObjectReturn<Boolean,String> doWithdrawal(String code) {
        ObjectReturn<Boolean,String> aReturn = new ObjectReturn<>(false,"");
        for (WithdrawalView.Withdrawal view : WithdrawalView.Withdrawal.values()) {
            if (view.getCode() == Integer.parseInt(code)) {
                aReturn = setAccountBalance(view.getValue());
            }
        }
        return aReturn;
    }

    public ObjectReturn<Boolean,String> doWithdrawalOther(int value) {
        Account account = accountRepository.getAccountById(loginModel.getId());
        ObjectReturn<Boolean,String> check = checkBalance(account.getBalance(), value);
        if (check.getO1().equals(true))
            for (Account entity : accountList) {
                if (entity.getId().compareTo(loginModel.getId()) == 0) {
                    entity.setBalance(entity.getBalance() - value);
                    addTransfer(new Transfer(loginModel.getId(), "Withdrawal", value));
                    break;
                }
            }
        return check;
    }

    private int getAmountATM() {
        int amount = 0;
        for (Bill entity : billList) {
            amount += (entity.getValue() * entity.getQuantity());
        }
        return amount;
    }

    /**
     * @param among = sum of money
     * @param value = value to check when sub 50 000
     * @return
     */
    private ObjectReturn<Boolean,String> checkBalance(int among, int value) {
        ObjectReturn<Boolean,String> check = new ObjectReturn<>(false,"");
        if ((among - value) >= 50000) {
            new WithdrawController().printBillAndQua(value);
            return check.setO1(true);
        }
        return check.setO1(false).setO2("Your account balance is insufficient for withdrawal");
    }

    @Override
    public ObjectReturn<Boolean,String> setAccountBalance(int value) {
        int balance = accountRepository.getAccountBalance(loginModel.getId());
        ObjectReturn<Boolean,String> check = checkBalance(balance, value);
        if (check.getO1().equals(true)) {
            accountRepository.setBalanceById(loginModel.getId(), value);
            addTransfer(new Transfer(loginModel.getId(), "Withdrawal", value));
        }
        return check;
    }

    @Override
    public boolean addTransfer(Transfer entity) {
        return transfer.addTransfer(entity);
    }

    /**
     * check checkAmountFifty(value) || getAmountATM()
     *
     * @param value value of money to check
     * @return true if checkAmountFifty(value) && getAmountATM()
     */
    @Override
    public boolean checkBalance(int value) {
        return value <= 0 || !checkAmountFifty(value) || (getAmountATM() < value);
    }
}
