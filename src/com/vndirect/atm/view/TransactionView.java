package com.vndirect.atm.view;

import com.vndirect.atm.model.TransactionModel;

import java.util.List;

public interface TransactionView {

    String printUITransaction();

    void printTransaction(List<TransactionModel> list);
}
