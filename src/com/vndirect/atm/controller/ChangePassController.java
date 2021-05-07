package com.vndirect.atm.controller;


import com.vndirect.atm.model.ObjectReturn;
import com.vndirect.atm.service.ChangePassService;
import com.vndirect.atm.service.impl.ImplChangePassService;
import com.vndirect.atm.view.View;
import com.vndirect.atm.view.impl.AbstractView;
import com.vndirect.atm.view.impl.ConsoleChangePassView;

public class ChangePassController {

    private ConsoleChangePassView changePassView = ConsoleChangePassView.getInstance();
    private static ChangePassController changePassController = new ChangePassController();
    private ChangePassService changePassService = ImplChangePassService.getInstance();
    View view = AbstractView.getInstance();

    public static ChangePassController getInstance() {
        return changePassController;
    }

    public void changPass() {
        try {
            ObjectReturn<Boolean,String> check = checkChangePass();
            view.printString(check.getO2());
            if (check.getO1().equals(true)) {
                MenuController.getInstance().viewFunctionMenu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ObjectReturn<Boolean, String> checkChangePass(){
        String newPass = printUIChangePass();
        ObjectReturn<Boolean, String> aReturn = changePassService.compareOldPass(newPass);
        while (aReturn.getO1().equals(true)) {
            view.printString(aReturn.getO2());
            newPass = printUIReChangePass();
            aReturn = changePassService.compareOldPass(newPass);
        }
        return changePassService.toChangePass(newPass);
    }

    public String printUIChangePass() {
        String newPass = "";
        try {
            newPass = changePassView.printUIChangePass();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPass;
    }

    public String printUIReChangePass() {
        String newPass = "";
        try {
            newPass = changePassView.printUIReChangePass();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPass;
    }
}
