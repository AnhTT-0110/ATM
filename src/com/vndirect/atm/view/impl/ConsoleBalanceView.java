package com.vndirect.atm.view.impl;

import com.vndirect.atm.model.AccountModel;
import com.vndirect.atm.view.BalanceView;


public class ConsoleBalanceView extends AbstractView implements BalanceView {

    private static ConsoleBalanceView balanceView = new ConsoleBalanceView();

    public static ConsoleBalanceView getInstance() {
        return balanceView;
    }

    @Override
    public void printUIBalance(AccountModel accountModel) {
        printTwoStringFormat("%30s%n", "Information Account");
        printString("______________________________________________________");
        printString(accountModel.toString());
        printString("______________________________________________________");
    }

}
