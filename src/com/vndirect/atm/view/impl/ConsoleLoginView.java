package com.vndirect.atm.view.impl;

import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.view.LoginView;


public class ConsoleLoginView extends AbstractView implements LoginView {

    private LoginModel account = new LoginModel();
    private static ConsoleLoginView loginView = new ConsoleLoginView();

    @Override
    public void printUI() {
        printString("____=== Welcome to ATM ===____");
    }

    public static ConsoleLoginView getInstance() {
        return loginView;
    }

    @Override
    public String printUIToInputID() {
        printString("Input your Account ID: ");
        String accountID = scanner.nextLine();
        accountID = accountID.replaceAll(" ", "");
        return accountID;
    }

    @Override
    public String printUIToInputPass() {
        printString("Input your Account Password: ");
        return scanner.nextLine();
    }

    @Override
    public LoginModel getAccount() {
        return account;
    }
}
