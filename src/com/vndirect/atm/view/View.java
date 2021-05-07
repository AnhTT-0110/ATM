package com.vndirect.atm.view;

public interface View {

    default void printString(String string) {
        if (!string.equals(""))
            System.out.println(string);
    }

    default void printTwoStringFormat(String stringFormat, String string) {
        System.out.printf(stringFormat, string);
    }

    default void printThreeStringFormat(String stringFormat, String string1, String string2) {
        System.out.printf(stringFormat, string1, string2);
    }

}
