package com.vndirect.atm.view.impl;

import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.service.impl.ImplLoginService;
import com.vndirect.atm.view.MenuView;


public class ConsoleMenuView extends AbstractView implements MenuView {

    private LoginModel loginAccount = LoginModel.getInstance();
    private static ConsoleMenuView consoleMenuView = new ConsoleMenuView();
    public static ConsoleMenuView getInstance() {
        return consoleMenuView;
    }

    @Override
    public String functionMenu() {
        LoginModel loginModel =  ImplLoginService.getInstance().getLoginModel(loginAccount.getId());
        printString("_________________***_______________***_______________");
        printString("Welcome " + loginModel.getName() + ", to ATM Simple");
        for (MenuOption option : MenuOption.values()) {
            printString(String.valueOf(option));
        }
        printString("======================================================");
        printString("Please input your choice: ");
        return scanner.nextLine();
    }


    public String printMenu() {
        printString("1. Menu");
        printString("0. Logout");
        return scanner.nextLine();
    }

}
