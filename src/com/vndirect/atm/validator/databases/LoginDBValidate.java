package com.vndirect.atm.validator.databases;

import com.vndirect.atm.controller.LoginController;
import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.repository.impl.ImplAccountRepository;

import java.util.List;

public class LoginDBValidate {

    private ImplAccountRepository repositoryAccount = ImplAccountRepository.getInstance();
    private List<Account> accountList = repositoryAccount.getAllAccount();
    private static LoginDBValidate loginDBValidate = new LoginDBValidate();

    public static LoginDBValidate getInstance() {
        return loginDBValidate;
    }

    public ObjectReturn<Integer, String> checkAccount(String accountID) {
        ObjectReturn<Integer, String> check = new ObjectReturn<>(0, "");
        for (Account entity : accountList) {
            if (accountID.compareTo(entity.getId()) == 0) {
                if (!checkAccountLock(entity.getId())) {
                    return check.setO1(-1).setO2("Your Account is Lock");
                } else {
                    check.setO1(1);
                    break;
                }
            }
        }
        if (check.getO1().equals(0)) {
            check.setO2("Account isn't exist !");
        }
        return check;
    }

    private boolean checkAccountLock(String accountID) {
        boolean check = false;
        for (Account entity : accountList) {
            if (entity.getId().compareTo(accountID) == 0 && entity.isState()) {
                check = true;
                break;
            }
        }
        return check;
    }
}
