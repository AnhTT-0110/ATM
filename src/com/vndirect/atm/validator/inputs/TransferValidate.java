package com.vndirect.atm.validator.inputs;

import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.validator.databases.TransferDBValidate;

public class TransferValidate {

    private static TransferValidate transferValidate = new TransferValidate();

    public static TransferValidate getInstance() {
        return transferValidate;
    }

    public ObjectReturn<Boolean, String> checkValidateID1(String accountID) {
        ObjectReturn<Boolean, String> checkIsAccount = TransferDBValidate.getInstance().checkAccount(accountID);
        if (accountID.length() == 8 && accountID.substring(0, 4).compareTo("0800") == 0) {
            if (checkIsAccount.getO1().equals(true))
                return checkIsAccount;
        } else {
            checkIsAccount.setO1(false).setO2("ID must have 8 character and Begin is 0800");
        }
        return checkIsAccount;
    }

}
