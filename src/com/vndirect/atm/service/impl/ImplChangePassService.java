package com.vndirect.atm.service.impl;

import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.repository.AccountRepository;
import com.vndirect.atm.repository.impl.ImplAccountRepository;
import com.vndirect.atm.service.ChangePassService;
import com.vndirect.atm.validator.databases.ChangePassBDValidate;


public class ImplChangePassService implements ChangePassService {

    private static ImplChangePassService changePassService = new ImplChangePassService();
    private final AccountRepository repositoryAccount = ImplAccountRepository.getInstance();
    private LoginModel account = LoginModel.getInstance();

    public static ImplChangePassService getInstance() {
        return changePassService;
    }

    @Override
    public ObjectReturn<Boolean, String> compareOldPass(String passAccount) {
        return new ChangePassBDValidate().compareOldPass(passAccount);
    }

    @Override
    public ObjectReturn toChangePass(String newPass) {
        boolean check = repositoryAccount.setPassWord(account.getId(), newPass);
        ObjectReturn<Boolean, String> aReturn = new ObjectReturn<>();
        if (check) {
            aReturn.setO1(true).setO2("Change password success!");
        } else
            aReturn.setO1(false).setO2("Change password fails!");
        return aReturn;
    }

}
