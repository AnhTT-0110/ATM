package com.vndirect.atm.service;

import com.vndirect.atm.model.ObjectReturn;

public interface ChangePassService {

    ObjectReturn<Boolean,String> compareOldPass(String passAccount);

    ObjectReturn<Boolean,String> toChangePass(String newPass);
}
