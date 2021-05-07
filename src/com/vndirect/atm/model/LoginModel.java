package com.vndirect.atm.model;

public class LoginModel {

    private String id;
    private String pass;
    private String name;
    private int numberWrongPass;
    private static LoginModel account = new LoginModel();

    public static LoginModel getInstance() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setAccount(String accountID, String accountPass, String accountName) {
        account.id = accountID;
        account.pass = accountPass;
        account.name = accountName;
    }

    public LoginModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public LoginModel setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public LoginModel setNumberWrongPass(int numberWrongPass) {
        this.numberWrongPass = numberWrongPass;
        return this;
    }

    public static void setNull() {
        account.id = "";
        account.pass = "";
        account.name = "";
    }

    public int getNumberWrongPass() {
        return numberWrongPass;
    }
}
