package com.vndirect.atm.repository.impl;

import com.vndirect.atm.entitty.Transfer;
import com.vndirect.atm.repository.TransferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ImplTransferRepository implements TransferRepository {

    private static List<Transfer> transferList = new ArrayList<>();
    private static ImplTransferRepository transferRepository = new ImplTransferRepository();

    static {
        Transfer transfer1 = new Transfer("08000001", "08000002", 50000);
        Transfer transfer2 = new Transfer("08000002", "08000003", 200000);
        Transfer transfer3 = new Transfer("08000001", "08000004", 10000);
        Transfer transfer4 = new Transfer("08000002", "08000004", 500000);
        transferList.add(transfer1);
        transferList.add(transfer2);
        transferList.add(transfer3);
        transferList.add(transfer4);
    }

    public static ImplTransferRepository getInstance() {
        return transferRepository;
    }

    @Override
    public boolean addTransfer(Transfer entity) {
        transferList.add(entity);
        return true;
    }

    @Override
    public List<Transfer> getAllTransferById(String accountID) {
        List<Transfer> listTransfer = new ArrayList<>();
        for (Transfer entity : transferList) {
            if (entity.getFromAccount().compareTo(accountID) == 0 || entity.getToAccount().compareTo(accountID) == 0)
                listTransfer.add(entity);
        }
        return listTransfer;
    }

    public List<Transfer> getAllTransferById1(String accountID) {
        Predicate<Transfer> predicate = trans -> trans.getFromAccount().equals(accountID) || trans.getToAccount().equals(accountID);
        return transferList.stream().filter(predicate).collect(Collectors.toList());
    }

}
