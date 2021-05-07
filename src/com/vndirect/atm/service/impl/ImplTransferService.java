package com.vndirect.atm.service.impl;

import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.entitty.Transfer;
import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.repository.AccountRepository;
import com.vndirect.atm.repository.TransferRepository;
import com.vndirect.atm.repository.impl.ImplAccountRepository;
import com.vndirect.atm.repository.impl.ImplTransferRepository;
import com.vndirect.atm.service.TransferService;


public class ImplTransferService implements TransferService {

    private AccountRepository repositoryAccount = ImplAccountRepository.getInstance();
    private TransferRepository repositoryTransfer = ImplTransferRepository.getInstance();
    private static ImplTransferService transferService = new ImplTransferService();
    private static LoginModel loginModel = LoginModel.getInstance();

    public static ImplTransferService getInstance() {
        return transferService;
    }

    @Override
    public ObjectReturn<Boolean, String> transferToAccount(String accountID,String among) {
        ObjectReturn<Boolean, String> checkDeduct = deductFromAccount(Integer.parseInt(among));
        if (checkDeduct.getO1().equals(true)) {
            repositoryAccount.setBalanceById(accountID, -Integer.parseInt(among));
            addTransfer(new Transfer(loginModel.getId(), accountID, Integer.parseInt(among)));
            return checkDeduct.setO1(true);
        }
        return checkDeduct;
    }

    /**
     * deduct money from the account
     *
     * @param value money to deduct
     * @return true if deduct success
     */
    @Override
    public ObjectReturn<Boolean, String> deductFromAccount(int value) {
        Account account = repositoryAccount.getAccountById(loginModel.getId());
        ObjectReturn<Boolean, String> checkBalance = checkBalance(account.getBalance(), value);
        if (checkBalance.getO1().equals(false))
            return checkBalance;
        return checkBalance.setO1(repositoryAccount.setBalanceById(account.getId(), value)).setO2("");
    }

    @Override
    public boolean addTransfer(Transfer entity) {
        return repositoryTransfer.addTransfer(entity);
    }

    private ObjectReturn<Boolean, String> checkBalance(int among, int value) {
        ObjectReturn<Boolean, String> aReturn = new ObjectReturn<>(false, "");
        if ((among - value) >= 50000) {
            return aReturn.setO1(true).setO2("");
        } else {
            return aReturn.setO1(false).setO2("Your account balance is insufficient for withdrawal");
        }
    }
}
