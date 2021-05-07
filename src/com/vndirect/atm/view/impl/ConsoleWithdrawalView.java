package com.vndirect.atm.view.impl;

import com.vndirect.atm.entitty.Bill;
import com.vndirect.atm.service.impl.ImplWithdrawService;
import com.vndirect.atm.util.Helpers;
import com.vndirect.atm.view.WithdrawalView;

import java.util.List;

public class ConsoleWithdrawalView extends AbstractView implements WithdrawalView {

    private static ConsoleWithdrawalView withdrawalView = new ConsoleWithdrawalView();

    public static ConsoleWithdrawalView getInstance() {
        return withdrawalView;
    }

    @Override
    public String printUIWithdrawal() {
        for (Withdrawal option : Withdrawal.values()) {
            printString(String.valueOf(option));
        }
        return scanner.nextLine();
    }

    @Override
    public String printUIWithdrawalOther() {
        printString("Please, Input your amount of money: ");
        printString("Warning: Amount must multiple of 50 000 VNĐ ");
        String amount = scanner.nextLine();
        return amount.replace(" ", "");
    }

    @Override
    public void printBillAndQua(int value) {
        List<Bill> billEntities = new ImplWithdrawService().getBill(value);
        for (Bill entity : billEntities) {
            if (entity.getQuantity() != 0) {
                String bill = Helpers.convertMoneyVN(entity.getValue());
                printTwoStringFormat(bill + " VNĐ: " + entity.getQuantity() + " ; ", "");
            }
        }
        printString(" ");
    }
}
