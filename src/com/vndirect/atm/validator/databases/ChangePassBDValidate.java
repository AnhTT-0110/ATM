package com.vndirect.atm.validator.databases;

import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.repository.AccountRepository;
import com.vndirect.atm.repository.impl.ImplAccountRepository;

import java.util.List;

public class ChangePassBDValidate {

    AccountRepository repositoryAccount = ImplAccountRepository.getInstance();
    List<Account> accountEntityList = repositoryAccount.getAllAccount();

    private LoginModel account = LoginModel.getInstance();


    public ObjectReturn<Boolean, String> compareOldPass(String passAccount) {
        ObjectReturn<Boolean, String> objectReturn = new ObjectReturn<>(false, "");
        for (Account entity : accountEntityList) {
            boolean checkOldPass = (entity.getId().compareTo(account.getId()) == 0
                    && entity.getPass().compareTo(passAccount) == 0);
            if (checkOldPass) {
                return objectReturn.setO1(true).setO2("!!! New pass isn't similar to old pass");
            }
        }
        return objectReturn;
    }
}
