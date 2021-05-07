package com.vndirect.atm.entitty;

public class Account {

    private String id;
    private String name;
    private String pass;
    private int balance;
    private boolean state;

    public Account() {
    }

    public Account(String id, String name, String pass, int balance, boolean state) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.balance = balance;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


}
