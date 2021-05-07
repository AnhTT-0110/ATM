package com.vndirect.atm.entitty;

import java.util.Date;

public class Transfer {

    private String fromAccount;
    private String toAccount;
    private int amount;
    private Date time;
    private String tradingCode;
    private static int identityCode = 1;

    public Transfer() {
    }

    public Transfer(String fromAccount, String toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        time = new Date();
        tradingCode = "TSN000" + identityCode;
        identityCode++;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTradingCode() {
        return tradingCode;
    }
}
