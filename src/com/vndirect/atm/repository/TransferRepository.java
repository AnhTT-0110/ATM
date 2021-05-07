package com.vndirect.atm.repository;

import com.vndirect.atm.entitty.Transfer;

import java.util.List;

public interface TransferRepository {

    boolean addTransfer(Transfer entity);

    List<Transfer> getAllTransferById(String accountID);
}
