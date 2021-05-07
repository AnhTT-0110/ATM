package com.vndirect.atm.model;

import com.vndirect.atm.repository.impl.ImplAccountRepository;
import com.vndirect.atm.util.Helpers;

import java.util.Date;

public class TransactionModel {

    private String fromAccount;
    private String toAccount;
    private String nameAccount;
    private int amount;
    private Date time;
    private String tradingCode;
    private LoginModel loginModel = LoginModel.getInstance();

    public TransactionModel(String fromAccount, String toAccount, String nameAccount, int amount, Date time, String tradingCode) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.nameAccount = nameAccount;
        this.amount = amount;
        this.time = time;
        this.tradingCode = tradingCode;
    }

    @Override
    public String toString() {
        String among = Helpers.convertMoneyVN(amount);
        String accountID = "";
        if (fromAccount.compareTo(loginModel.getId()) == 0) {
            among = " - " + among;
            accountID = toAccount;
        } else {
            among = " + " + among;
            accountID = fromAccount;
        }
        String date = Helpers.convertDate(time);
        ImplAccountRepository repositoryAccount = new ImplAccountRepository();
        String nameAccount = repositoryAccount.getNameAccount(accountID);
        return String.format("%15s | %15s | %20s | %25s | %30s", tradingCode, accountID, nameAccount, among + " VNĐ", date);
    }
}
