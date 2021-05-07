package com.vndirect.atm.model;

public class ObjectReturn<O1, O2> {

    private O1 o1;
    private O2 o2;
    private static ObjectReturn objectReturn = new ObjectReturn();

    public ObjectReturn(O1 o1, O2 o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public static ObjectReturn getInstance() {
        return objectReturn;
    }

    public ObjectReturn() {
    }

    public ObjectReturn setObjectReturn(O1 o1, O2 o2) {
        objectReturn.o1 = o1;
        objectReturn.o2 = o2;
        return objectReturn;
    }

    public O1 getO1() {
        return o1;
    }

    public ObjectReturn<O1, O2> setO1(O1 o1) {
        this.o1 = o1;
        return this;
    }

    public O2 getO2() {
        return o2;
    }

    public ObjectReturn<O1, O2> setO2(O2 o2) {
        this.o2 = o2;
        return this;
    }
}