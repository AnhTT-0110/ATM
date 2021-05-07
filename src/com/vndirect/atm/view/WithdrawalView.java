package com.vndirect.atm.view;

import com.vndirect.atm.util.Helpers;

import java.util.NoSuchElementException;

public interface WithdrawalView {

    enum Withdrawal {

        TWO_THD(1, 200000),
        FIF_THD(2, 500000),
        ONE_MIN(3, 1000000),
        TWO_MIN(4, 2000000),
        FIF_MIN(5, 5000000),
        OTHER(6, -1),
        EXIT(0, 0);

        private final int code;
        private final int value;

        Withdrawal(int code, int value) {
            this.code = code;
            this.value = value;
        }

        public static Withdrawal fromCode(int code) {
            for (Withdrawal option : Withdrawal.values()) {
                if (option.code == code) {
                    return option;
                }
            }
            throw new NoSuchElementException("invalid menuOption with code " + code);
        }

        @Override
        public String toString() {
            String statement = code == 6 ? "Other" : "Menu";
            return code + ". " + (value > 0 ? Helpers.convertMoneyVN(value) + " VNĐ" : statement);
        }

        public int getCode() {
            return code;
        }

        public int getValue() {
            return value;
        }
    }

    String printUIWithdrawal();

    String printUIWithdrawalOther();

    void printBillAndQua(int value);

}
