package com.vndirect.atm.model;

import com.vndirect.atm.util.Helpers;
import com.vndirect.atm.view.View;
import com.vndirect.atm.view.impl.AbstractView;

public class AccountModel {

    private String accountID;
    private String accountName;
    private int accountBalance;
    private boolean accountState;

    public String getAccountID() {
        return accountID;
    }

    public AccountModel setAccountID(String accountID) {
        this.accountID = accountID;
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountModel setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public AccountModel setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }

    public boolean isAccountState() {
        return accountState;
    }

    public AccountModel setAccountState(boolean accountState) {
        this.accountState = accountState;
        return this;
    }
    public String getCurrentToString() {
        int accountCurrent = getAccountBalance();
        return Helpers.convertMoneyVN(accountCurrent) + " VNĐ";
    }

    @Override
    public String toString() {
        String stringFormat = "|%20s %30s |%n";
        View view = AbstractView.getInstance();
        view.printThreeStringFormat(stringFormat, "AccountID: ", getAccountID());
        view.printThreeStringFormat(stringFormat, "Account Name: ", getAccountName());
        view.printThreeStringFormat(stringFormat, "Account Balance: ", getCurrentToString());
        view.printThreeStringFormat(stringFormat, "Account State: ", String.valueOf(isAccountState()));
        return "";
    }
}
