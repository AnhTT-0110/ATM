package com.vndirect.atm.service.impl;

import com.vndirect.atm.controller.*;
import com.vndirect.atm.service.MenuService;
import com.vndirect.atm.view.impl.ConsoleMenuView;

import java.util.NoSuchElementException;

public class ImplMenuService implements MenuService {

    private static ImplMenuService menuService = new ImplMenuService();

    public static ImplMenuService getInstance() {
        return menuService;
    }

    @Override
    public void choiceFun(String code) throws NoSuchElementException,NumberFormatException {
            ConsoleMenuView.MenuOption option = ConsoleMenuView.MenuOption.fromCode(Integer.parseInt(code));
            switch (option) {
                case CHANGE_PASSWD:
                    ChangePassController.getInstance().changPass();
                    break;
                case CHECK_BAL:
                    BalanceController.getInstance().printUIBalance();
                    break;
                case CASH_WDR:
                    WithdrawController.getInstance().toWithdrawal();
                    break;
                case TRANS:
                    TransferController.getInstance().transferAccount();
                    break;
                case PRINT_STATE:
                    TransactionController.getInstance().executeFun();
                    break;
                case LOGOUT:
                    LoginController.getInstance().viewMenu();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                default:
                    System.out.println("invalid");
            }
        }



}
