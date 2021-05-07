package com.vndirect.atm.controller;

import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.service.impl.ImplWithdrawService;
import com.vndirect.atm.service.WithdrawService;
import com.vndirect.atm.view.WithdrawalView;
import com.vndirect.atm.view.impl.AbstractView;
import com.vndirect.atm.view.impl.ConsoleWithdrawalView;

import java.util.InputMismatchException;

public class WithdrawController {

    private static WithdrawController withdrawController = new WithdrawController();
    private WithdrawService withdrawService = ImplWithdrawService.getInstance();
    private WithdrawalView withdrawalView = ConsoleWithdrawalView.getInstance();
    private AbstractView abstractView = AbstractView.getInstance();

    public static WithdrawController getInstance() {
        return withdrawController;
    }

    public void toWithdrawal() {
        try {
            String choice = printUIWithdrawal();
            int choiceNumber = Integer.parseInt(choice);
            if (choiceNumber == WithdrawalView.Withdrawal.OTHER.getCode()) {
                directWithdrawalOther();
                return;
            }
            if (choiceNumber == WithdrawalView.Withdrawal.EXIT.getCode()) {
                System.exit(0);
            }
            ObjectReturn<Boolean, String> aReturn = withdrawService.doWithdrawal(choice);
            abstractView.printString(aReturn.getO2());
            MenuController.getInstance().viewFunctionMenu();

        } catch (InputMismatchException e) {
            abstractView.printString("You entered the validation error ");
            new MenuController().viewFunctionMenu();
        } catch (Exception exception) {
            abstractView.printString("An error occurred");
            new MenuController().viewFunctionMenu();
        }
    }

    public int printUIWithdrawalOther() {
        int value = 0;
        try {
            value = Integer.parseInt(withdrawalView.printUIWithdrawalOther());
        } catch (NumberFormatException exception) {
            abstractView.printString("Input isn't a valid number");
        }
        return value;
    }

    public String printUIWithdrawal() {
        String value = "";
        try {
            value = withdrawalView.printUIWithdrawal();
        } catch (NumberFormatException exception) {
            abstractView.printString("Input isn't a valid number");
        }
        return value;
    }


    public void printBillAndQua(int value) {
        try {
            withdrawalView.printBillAndQua(value);
        } catch (NumberFormatException exception) {
            abstractView.printString("Input isn't a valid number");
        }
    }

    public void directWithdrawalOther() {
        int value = 0;
        do {
            value = printUIWithdrawalOther();
        }
        while (withdrawService.checkBalance(value));
        ObjectReturn<Boolean, String> aReturn = withdrawService.doWithdrawalOther(value);
        abstractView.printString(aReturn.getO2());
        MenuController.getInstance().viewFunctionMenu();
    }
}
