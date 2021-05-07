package com.vndirect.atm.view;

import com.vndirect.atm.model.LoginModel;

public interface LoginView {

    void printUI();

    String printUIToInputID();

    String printUIToInputPass();

    LoginModel getAccount();

}
