package com.vndirect.atm.validator.inputs;

import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.validator.databases.LoginDBValidate;

public class LoginValidate {

    private static LoginValidate loginValidate = new LoginValidate();

    public static LoginValidate getInstance() {
        return loginValidate;
    }

    public ObjectReturn<Boolean,String> checkValidateID(String accountID) {
        ObjectReturn<Boolean,String> check = new ObjectReturn<>(false,"");
        if (accountID.length() == 8 && accountID.substring(0, 4).compareTo("0800") == 0) {
            LoginDBValidate loginDBValidate = LoginDBValidate.getInstance();
            ObjectReturn<Integer,String> checkIsAccount = loginDBValidate.checkAccount(accountID);
            if (checkIsAccount.getO1().equals(1)) {
                check.setO1(true);
            }
            return check.setO2(checkIsAccount.getO2());
        }
        return check.setO1(false).setO2("ID must have 8 character and Begin is 0800");
    }
}
