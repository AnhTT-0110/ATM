package com.vndirect.atm.validator.databases;


import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.repository.impl.ImplAccountRepository;

import java.util.List;

public class TransferDBValidate {

    private List<Account> accountList = ImplAccountRepository.getInstance().getAllAccount();
    private LoginModel account = LoginModel.getInstance();
    private static TransferDBValidate transferDB = new TransferDBValidate();

    public static TransferDBValidate getInstance(){
        return transferDB;
    }
    public ObjectReturn<Boolean,String> checkAccount(String accountID) {
        ObjectReturn<Boolean,String> check = new ObjectReturn<>(false,"");
        for (Account entity : accountList) {
            if (accountID.compareTo(entity.getId()) == 0 && accountID.compareTo(account.getId()) != 0) {
                check.setO1(true).setO2("");
                break;
            }
        }
        if (check.getO1().equals(false)) {
            return check.setO2("Account isn't validate !");
        }
        return check;
    }

}
