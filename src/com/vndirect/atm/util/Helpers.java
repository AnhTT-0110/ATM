package com.vndirect.atm.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helpers {

    public static final String language = "vi";
    public static final String country = "VN";

    public static String convertDate(Date day) {
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyy 'at' hh:mm:ss a");
        return ft.format(day);
    }

    public static String convertMoneyVN(int accountBalance) {
        return convertMoney(accountBalance, "vi", "VN");
    }

    public static String convertMoney(int accountBalance, String language, String country) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale(language, country));
        return numberFormat.format(accountBalance);
    }

}
