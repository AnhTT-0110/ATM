package com.vndirect.atm.view;

import java.util.NoSuchElementException;

public interface MenuView {

    enum MenuOption {
        CHANGE_PASSWD(1, "Change Pass"),
        CHECK_BAL(2, "Check balance"),
        CASH_WDR(3, "Cash withdrawals "),
        TRANS(4, "Transfer to other Account"),
        PRINT_STATE(5, "Print statements"),
        LOGOUT(6, "Logout"),
        EXIT(0, "Exit");

        private final int code;
        private final String value;

        MenuOption(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public static MenuOption fromCode(int code) {
            for (MenuOption option : MenuOption.values()) {
                if (option.code == code) {
                    return option;
                }
            }
            throw new NoSuchElementException("invalid menuOption with code " + code);
        }

        @Override
        public String toString() {
            return code + ". " + value;
        }
    }

    String functionMenu();

    String printMenu();

}
