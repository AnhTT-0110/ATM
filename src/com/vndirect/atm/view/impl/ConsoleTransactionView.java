package com.vndirect.atm.view.impl;

import com.vndirect.atm.controller.TransactionController;
import com.vndirect.atm.model.TransactionModel;
import com.vndirect.atm.view.TransactionView;

import java.util.List;

public class ConsoleTransactionView extends AbstractView implements TransactionView {

    private static ConsoleTransactionView transactionView = new ConsoleTransactionView();

    public static ConsoleTransactionView getInstance() {
        return transactionView;
    }

    @Override
    public String printUITransaction() {
        printString("Transaction History of You");
        new TransactionController().printListTransaction();
        printString("0. Menu");
        return scanner.nextLine();
    }

    @Override
    public void printTransaction(List<TransactionModel> transactionModels) {
        int count = transactionModels.size();
        if (count == 0) {
            printString("You have no transaction yet ");
            return;
        }
        System.out.printf("%s | %15s | %15s | %20s | %25s | %30s", " ", "Code Trading", "AccountID", "Account Name", "Among", "Time");
        printString("");
        printString("__________________________________________________________________________________________________________________");
        int identity = 1;
        for (TransactionModel model : transactionModels) {
            printTwoStringFormat("%s | " + model, String.valueOf(identity));
            identity++;
            printString(" ");
        }
    }
}
