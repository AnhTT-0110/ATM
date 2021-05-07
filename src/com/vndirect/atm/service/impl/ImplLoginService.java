package com.vndirect.atm.service.impl;

import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.repository.AccountRepository;
import com.vndirect.atm.repository.impl.ImplAccountRepository;
import com.vndirect.atm.service.LoginService;
import com.vndirect.atm.validator.inputs.LoginValidate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ImplLoginService implements LoginService {

    private static Map<String, Integer> loginSessions = new HashMap();
    private List<Account> accountList = ImplAccountRepository.getInstance().getAllAccount();
    private static ImplLoginService loginService = new ImplLoginService();
    private LoginModel account = LoginModel.getInstance();
    ObjectReturn<Integer, String> objectReturn = new ObjectReturn<>();

    public static ImplLoginService getInstance() {
        return loginService;
    }

    public ObjectReturn<Integer, String> checkPassAccount(String accountPass) {
        int check = 1;

        for (Account element : accountList) {
            if (element.getId().compareTo(account.getId()) == 0 &&
                    element.getPass().compareTo(accountPass) == 0) {
                setValueSession(element);
                return objectReturn.setO1(check).setO2("");
            }
        }
        objectReturn = checkWrongPass();
        return objectReturn;
    }

    private ObjectReturn checkWrongPass() {
        String mess = "";
        int check = 0;
        int numberWrong = account.getNumberWrongPass();
        boolean check3 = loginSessions.containsKey(account.getId());
        int number;
        if (check3) {
            number = loginSessions.get(account.getId());
        } else {
            number = numberWrong;
        }
        if (number < 2 && number >= 0) {
            account.setNumberWrongPass(number + 1);
            mess = "Warnings: There are " + (2 - number) + " times left to enter";
            loginSessions.put(account.getId(), number + 1);
            check = -1;
        } else {
            AccountRepository accountRes = ImplAccountRepository.getInstance();
            accountRes.setAccountStateById(account.getId());
            mess = "Account is lock because input error more 3!";
            check = 0;
        }
        objectReturn.setO1(check).setO2(mess);
        return objectReturn;
    }


    private boolean getAccountSession(String accountId) {
        Optional<Map.Entry<String, Integer>> optional = loginSessions.entrySet().stream().filter(a -> a.getKey().equals(accountId)).findFirst();
        return optional.isPresent();
    }

    private void setValueSession(Account entity) {
        account.setAccount(entity.getId(), entity.getPass(), entity.getName());
    }

    @Override
    public void setNull() {
        LoginModel.setNull();
    }


    @Override
    public LoginModel getLoginModel(String accountID) {
        AccountRepository account = ImplAccountRepository.getInstance();
        LoginModel loginModel = LoginModel.getInstance();
        String id = account.getAccountById(accountID).getId();
        String pass = account.getAccountById(accountID).getPass();
        String name = account.getAccountById(accountID).getName();
        loginModel.setAccount(id, pass, name);
        return loginModel;
    }

    @Override
    public ObjectReturn<Boolean, String> checkId(String accountId) {
        LoginValidate loginValidate = LoginValidate.getInstance();
        ObjectReturn<Boolean, String> aReturn = loginValidate.checkValidateID(accountId);
        account = new LoginModel();
        if (aReturn.getO1().equals(true)) {
            account.setId(accountId);
            ImplLoginService service = ImplLoginService.getInstance();
            return aReturn.setO1(true).setO2("Hello " + service.getLoginModel(accountId).getName());
        }
        return aReturn;
    }

}
