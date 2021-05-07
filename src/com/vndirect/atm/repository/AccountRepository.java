package com.vndirect.atm.repository;

import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.model.AccountModel;

import java.util.List;

public interface AccountRepository {

    void setAccountStateById(String accountId);

    Account getAccountById(String accountId);

    int getAccountBalance(String accountId);

    List<Account> getAllAccount();

//    boolean setPassWord(String accountId, String accountPass);
    boolean setPassWord(String accountId, String accountPass);

    AccountModel getAccountModel(String accountId);

    boolean setBalanceById(String accountID, int value);

    String getNameAccount(String accountID);
}
