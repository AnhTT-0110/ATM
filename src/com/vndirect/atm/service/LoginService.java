package com.vndirect.atm.service;

import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.model.ObjectReturn;

public interface LoginService {

    void setNull();

    LoginModel getLoginModel(String accountId);

    ObjectReturn<Boolean,String> checkId(String accountId);

}