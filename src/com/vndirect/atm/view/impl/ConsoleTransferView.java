package com.vndirect.atm.view.impl;

import com.vndirect.atm.repository.impl.ImplAccountRepository;
import com.vndirect.atm.view.TransferView;

public class ConsoleTransferView extends AbstractView implements TransferView {

    private static ConsoleTransferView transactionView = new ConsoleTransferView();

    public static ConsoleTransferView getInStance(){
        return transactionView;
    }
    @Override
    public String printUITransfer() {
        printString("Input Account ID other to transfer: ");
        printString("0. Menu");
        String anotherAccount = scanner.nextLine();
        anotherAccount = anotherAccount.replace(" ", "");
        return anotherAccount;
    }

    @Override
    public String printUIInputMoney(String accountID) {
        String accountName = new ImplAccountRepository().getNameAccount(accountID);
        printString("Input money you want to transfer " + accountName + " : ");
        printString("0. Menu");
        String among = scanner.nextLine();
        among = among.replace(" ", "");
        return among;
    }
}
