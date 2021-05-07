package com.vndirect.atm.controller;

import com.vndirect.atm.service.impl.ImplTransactionService;
import com.vndirect.atm.model.TransactionModel;
import com.vndirect.atm.service.TransactionService;
import com.vndirect.atm.view.TransactionView;
import com.vndirect.atm.view.impl.ConsoleTransactionView;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {

    private TransactionView transactionView = ConsoleTransactionView.getInstance();
    private TransactionService transactionService = ImplTransactionService.getInstance();
    private static TransactionController transactionController = new TransactionController();

    public static TransactionController getInstance() {
        return transactionController;
    }

    public String printUITransaction() {
        String valueIn = "";
        try {
            valueIn = transactionView.printUITransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            MenuController.getInstance().viewFunctionMenu();
        }
        return valueIn;
    }

    public List<TransactionModel> getTransactionModel() {
        List<TransactionModel> transactionList = new ArrayList<>();
        try {
            transactionList = transactionService.getTransactionModel();
        } catch (Exception exception) {
            exception.printStackTrace();
            MenuController.getInstance().viewFunctionMenu();
        }
        return transactionList;
    }

    public void printListTransaction() {
        try {
            transactionView.printTransaction(getTransactionModel());
        } catch (Exception exception) {
            exception.printStackTrace();
            MenuController.getInstance().viewFunctionMenu();
        }
    }

    public void executeFun() {
        try {
            String choice = "";
            do {
                choice = printUITransaction();
            }
            while (choice.compareTo("0") != 0);
            if (choice.compareTo("0") == 0)
                MenuController.getInstance().viewFunctionMenu();
        } catch (Exception exception) {
            exception.printStackTrace();
            MenuController.getInstance().viewFunctionMenu();
        }
    }
}
