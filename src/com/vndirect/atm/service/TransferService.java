package com.vndirect.atm.service;

import com.vndirect.atm.entitty.Transfer;
import com.vndirect.atm.model.ObjectReturn;

public interface TransferService {

    ObjectReturn<Boolean, String> transferToAccount(String accountID,String among);

    ObjectReturn<Boolean, String> deductFromAccount(int value);

    boolean addTransfer(Transfer entity);
}
