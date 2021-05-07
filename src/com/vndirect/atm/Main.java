package com.vndirect.atm;

import com.vndirect.atm.controller.LoginController;
import com.vndirect.atm.view.impl.ConsoleLoginView;

public class Main {

    public static void main(String[] args) {

        ConsoleLoginView.getInstance().printUI();
        LoginController.getInstance().viewMenu();

    }

}
