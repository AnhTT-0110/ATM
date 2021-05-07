package com.vndirect.atm.service.impl;

import com.vndirect.atm.entitty.Transfer;
import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.model.TransactionModel;
import com.vndirect.atm.repository.AccountRepository;
import com.vndirect.atm.repository.TransferRepository;
import com.vndirect.atm.repository.impl.ImplAccountRepository;
import com.vndirect.atm.repository.impl.ImplTransferRepository;
import com.vndirect.atm.service.TransactionService;

import java.util.ArrayList;
import java.util.List;

public class ImplTransactionService implements TransactionService {

    private static ImplTransactionService transactionService = new ImplTransactionService();
    private TransferRepository repositoryTransfer = new ImplTransferRepository();
    private AccountRepository repositoryAccount = new ImplAccountRepository();
    private LoginModel login = LoginModel.getInstance();

    public static ImplTransactionService getInstance() {
        return transactionService;
    }

    @Override
    public List<TransactionModel> getTransactionModel() {
        List<TransactionModel> modelList = new ArrayList<>();
        List<Transfer> transferList = repositoryTransfer.getAllTransferById(login.getId());
        for (Transfer entity : transferList) {
            String nameAccount = repositoryAccount.getAccountById(entity.getToAccount()).getName();
            modelList.add(new TransactionModel(entity.getFromAccount(), entity.getToAccount(), nameAccount, entity.getAmount(), entity.getTime(), entity.getTradingCode()));
        }
        return modelList;
    }

}
