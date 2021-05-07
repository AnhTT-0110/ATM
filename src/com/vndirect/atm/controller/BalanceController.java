package com.vndirect.atm.controller;

import com.vndirect.atm.entitty.Account;
import com.vndirect.atm.model.AccountModel;
import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.repository.AccountRepository;
import com.vndirect.atm.repository.impl.ImplAccountRepository;
import com.vndirect.atm.view.BalanceView;
import com.vndirect.atm.view.impl.ConsoleBalanceView;

public class BalanceController {

    private static BalanceController balanceController = new BalanceController();
    private static BalanceView balanceView = ConsoleBalanceView.getInstance();
    private LoginModel loginModel = LoginModel.getInstance();
    private AccountRepository accountRepository = ImplAccountRepository.getInstance();
    public static BalanceController getInstance() {
        return balanceController;
    }

    public void printUIBalance() {
        try {
            AccountModel accountModel = accountRepository.getAccountModel(loginModel.getId());
            balanceView.printUIBalance(accountModel);
            MenuController.getInstance().direct();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
