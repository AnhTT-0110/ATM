package com.vndirect.atm.service;
import com.vndirect.atm.entitty.Bill;
import com.vndirect.atm.entitty.Transfer;
import com.vndirect.atm.model.ObjectReturn;

import java.util.List;

public interface WithdrawService {

    List<Bill> getBill(int value);

    ObjectReturn<Boolean,String> doWithdrawal(String code);

    ObjectReturn<Boolean,String> setAccountBalance(int value);

    boolean addTransfer(Transfer entity);

    boolean checkBalance(int value);

    ObjectReturn<Boolean,String> doWithdrawalOther(int value);
}
