package com.vndirect.atm.controller;

import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.service.impl.ImplLoginService;
import com.vndirect.atm.view.impl.AbstractView;
import com.vndirect.atm.view.impl.ConsoleLoginView;

public class LoginController {

    private static LoginController loginController = new LoginController();
    private ConsoleLoginView loginView = ConsoleLoginView.getInstance();
    private ImplLoginService implLoginService = ImplLoginService.getInstance();

    public static LoginController getInstance() {
        return loginController;
    }

    public void printUILoginId() {
        try {
            ObjectReturn<Boolean, String> aReturn;
            String idAccount = loginView.printUIToInputID();
            aReturn = implLoginService.checkId(idAccount);
            AbstractView.getInstance().printString(aReturn.getO2());
            if (Boolean.TRUE.equals(aReturn.getO1())) {
                printUILoginPass();
                return;
            }
            printUILoginId();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void printUILoginPass() {
        try {
            String accountPass = loginView.printUIToInputPass();
            directInPass(accountPass);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void directInPass(String accountPass) {
        ObjectReturn<Integer, String> aReturn = implLoginService.checkPassAccount(accountPass);
        String string = aReturn.getO2();
        AbstractView.getInstance().printString(string);
        if (aReturn.getO1() == 1) {
            MenuController.getInstance().viewFunctionMenu();
        } else if (aReturn.getO1() == -1) {
            loginController.printUILoginPass();
        } else {
            loginController.printUILoginId();
        }
    }

    public void viewMenu() {
        try {
            implLoginService.setNull();
            loginController.printUILoginId();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
