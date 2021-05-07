package com.vndirect.atm.view.impl;

import com.vndirect.atm.view.ChangePassView;


public class ConsoleChangePassView extends AbstractView implements ChangePassView {

    private static ConsoleChangePassView consoleChangePassView =  new ConsoleChangePassView();

    public static ConsoleChangePassView getInstance(){
        return consoleChangePassView;
    }

    @Override
    public String printUIChangePass() {
        String newPass = "";
        printString("Please, Input your new password");
        newPass = scanner.nextLine();
        return newPass;
    }

    @Override
    public String printUIReChangePass() {
        String newPass = "";
        printString("Input your new password again");
        newPass = scanner.nextLine();
        return newPass;
    }
}
