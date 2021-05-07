package com.vndirect.atm.entitty;

public class Bill {

    private int value;
    private String name;
    private int quantity;

    public Bill(int value, int quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    public Bill(int value, String name, int quantity) {
        this.value = value;
        this.name = name;
        this.quantity = quantity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
