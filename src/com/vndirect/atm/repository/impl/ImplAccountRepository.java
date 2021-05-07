package com.vndirect.atm.repository.impl;

import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.model.AccountModel;
import com.vndirect.atm.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ImplAccountRepository implements AccountRepository {

    private static List<Account> accountEntityList = new ArrayList<>();
    private static ImplAccountRepository accountRepository = new ImplAccountRepository();

    static {
        Account accountEntity1 = new Account("08000001", "TRIEU THI ANH", "1234", 49000, true);
        Account accountEntity2 = new Account("08000002", "TRIEU THI PHUONG", "1234", 50000000, true);
        Account accountEntity3 = new Account("08000003", "TRIEU VAN KHANH ", "1234", 50000000, true);
        Account accountEntity4 = new Account("08000004", "TRIEU QUANG DUY", "1234", 49000, false);
        Account accountEntity5 = new Account("08000005", "TRIEU QUANG SANG", "1234", 20000000, true);
        accountEntityList.add(accountEntity1);
        accountEntityList.add(accountEntity2);
        accountEntityList.add(accountEntity3);
        accountEntityList.add(accountEntity4);
        accountEntityList.add(accountEntity5);
    }

    public static ImplAccountRepository getInstance() {
        return accountRepository;
    }

    @Override
    public void setAccountStateById(String accountId) {
        for (Account entity : accountEntityList) {
            if (entity.getId().compareTo(accountId) == 0) {
                entity.setState(false);
                return;
            }
        }
    }

    public List<Account> filter(Predicate<Account> predicate) {
        return accountEntityList.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<Account> getAllAccount() {
        return accountEntityList;
    }

    //    @Override
    //    public boolean setPassWord(String accountId, String accountPass) {
    //        for (Account entity : accountEntityList) {
    //            if (entity.getId().compareTo(accountId) == 0) {
    //                entity.setPass(accountPass);
    //                return true;
    //            }
    //        }
    //        return false;
    //    }
    @Override
    public boolean setPassWord(String accountId, String accountPass) {
        Optional<Account> optional = accountEntityList.stream().filter(account -> account.getId().equals(accountId)).findFirst();
        if (optional.isPresent()) {
            optional.get().setPass(accountPass);
            return true;
        }
        return false;
    }

    @Override
    public AccountModel getAccountModel(String accountId) {
        AccountModel accountModel = new AccountModel();
        //        for (Account entity : accountEntityList) {
        //            if (entity.getId().compareTo(accountId) == 0) {
        //                accountModel.setAccountID(entity.getId());
        //                accountModel.setAccountName(entity.getName());
        //                accountModel.setAccountBalance(entity.getBalance());
        //                accountModel.setAccountState(entity.isState());
        //            }
        //        }
        Optional<Account> account = accountEntityList.stream().filter(e -> e.getId().equals(accountId)).findFirst();
        if (account.isPresent()) {
            accountModel.setAccountID(account.get().getId());
            accountModel.setAccountName(account.get().getName());
            accountModel.setAccountBalance(account.get().getBalance());
            accountModel.setAccountState(account.get().isState());
        }
        return accountModel;
    }

    @Override
    public boolean setBalanceById(String accountID, int value) {
//        for (Account entity : accountEntityList) {
//            if (entity.getId().compareTo(accountID) == 0) {
//                int balance = entity.getBalance();
//                entity.setBalance(balance - value);
//                return true;
//            }
//        }
//        return false;
        accountEntityList
                .stream()
                .filter(e -> e.getId().equals(accountID))
                .forEach(e -> e.setBalance(e.getBalance() - value));
        return true;
    }

    @Override
    public String getNameAccount(String accountID) {
        String nameAccount = "";
//        for (Account entity : accountEntityList) {
//            if (entity.getId().compareTo(accountID) == 0) {
//                nameAccount = entity.getName();
//            }
//        }
        Optional<Account> account = accountEntityList
                .stream()
                .filter(e -> e.getId().equals(accountID))
                .findFirst();
        if (account.isPresent()) {
            nameAccount = account.get().getName();
        }
        return nameAccount;
    }

    @Override
    public Account getAccountById(String accountId) {
        Account accountEntity = new Account();
//        for (Account entity : accountEntityList) {
//            if (entity.getId().compareTo(accountId) == 0) {
//                accountEntity = entity;
//            }
//        }
        Optional<Account> account = accountEntityList
                .stream()
                .filter(e -> e.getId().equals(accountId))
                .findFirst();
        if (account.isPresent()) {
            accountEntity = account.get();
        }
        return accountEntity;
    }

    @Override
    public int getAccountBalance(String accountId) {
//        for (Account entity : accountEntityList) {
//            if (entity.getId().compareTo(accountId) == 0) {
//                return entity.getBalance();
//            }
//        }
//        return 0;

        Optional<Account> account = accountEntityList
                .stream()
                .filter(e -> e.getId().equals(accountId))
                .findFirst();
        return account.map(Account::getBalance).orElse(0);
    }


}
