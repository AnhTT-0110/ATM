package com.vndirect.atm.repository.impl;

import com.vndirect.atm.entitty.Bill;
import com.vndirect.atm.repository.BillRepository;

import java.util.ArrayList;
import java.util.List;

public class ImplBillRepository implements BillRepository {

    private static ImplBillRepository billRepository = new ImplBillRepository();
    private static List<Bill> billEntityList = new ArrayList<>();

    public static ImplBillRepository getInstance() {
        return billRepository;
    }

    static {
        Bill fifty = new Bill(50000, "Fifty thousand", 200);
        Bill oneHundred = new Bill(100000, "One hundred", 200);
        Bill twoHundred = new Bill(200000, "Two hundred", 200);
        Bill fiveHundred = new Bill(500000, "Five hundred", 100);
        billEntityList.add(fifty);
        billEntityList.add(oneHundred);
        billEntityList.add(twoHundred);
        billEntityList.add(fiveHundred);
    }

    /**
     * setQuantity for Bill in List
     *
     * @param index : index of Bill
     */
    @Override
    public void setQuantity(int index) {
        billEntityList.get(index).setQuantity(billEntityList.get(index).getQuantity() - 1);
    }

    @Override
    public List<Bill> getAllBill() {
        return billEntityList;
    }

}
