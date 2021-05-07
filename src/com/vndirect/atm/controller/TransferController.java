package com.vndirect.atm.controller;

import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.service.TransferService;
import com.vndirect.atm.service.impl.ImplTransferService;
import com.vndirect.atm.validator.inputs.TransferValidate;
import com.vndirect.atm.view.TransferView;
import com.vndirect.atm.view.impl.AbstractView;
import com.vndirect.atm.view.impl.ConsoleTransferView;

public class TransferController {

    private TransferView transferView = ConsoleTransferView.getInStance();
    private TransferService transferService = ImplTransferService.getInstance();
    private static TransferController transferController = new TransferController();
    private AbstractView abstractView = AbstractView.getInstance();

    public static TransferController getInstance() {
        return transferController;
    }

    public String printUITransfer() {
        String valueIn = "";
        try {
            valueIn = transferView.printUITransfer();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return valueIn;
    }
    
    public void transferAccount() {
        try {
            if (toTransfer()) {
                abstractView.printString("Transfer success!");
                MenuController.getInstance().viewFunctionMenu();
            } else {
                transferAccount();
            }
        } catch (NumberFormatException numberFormatException) {
            abstractView.printString("Input isn't a valid number");
            transferAccount();
        } catch (Exception exception) {
            abstractView.printString("An error occurred");
        }
    }

    public boolean toTransfer() {
        String accountId = printUITransfer();
        if (accountId.compareTo("0") == 0) {
            new MenuController().viewFunctionMenu();
            return false;
        }
        ObjectReturn<Boolean, String> checkValidateID = TransferValidate.getInstance().checkValidateID1(accountId);
        if (checkValidateID.getO1().equals(true)) {
            ObjectReturn<Boolean, String> transferToAccount = transferToAccount(accountId);
            abstractView.printString(transferToAccount.getO2());
            return transferToAccount.getO1();
        } else
            abstractView.printString(checkValidateID.getO2());
        return checkValidateID.getO1();
    }

    private String printUIInputMoney(String accountID){
        String among = "";
        do {
            among = transferView.printUIInputMoney(accountID);
        }
        while (Integer.parseInt(among) < 0);
        if (among.equals("0")) {
            new MenuController().viewFunctionMenu();
        }
        return among;
    }

    private ObjectReturn<Boolean, String> transferToAccount(String accountId){
        String among = printUIInputMoney(accountId);
        return transferService.transferToAccount(accountId,among);
    }
}
