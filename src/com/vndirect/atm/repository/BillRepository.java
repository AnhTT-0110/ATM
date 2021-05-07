package com.vndirect.atm.repository;

import com.vndirect.atm.entitty.Bill;

import java.util.List;

public interface BillRepository {

    void setQuantity(int index);

    List<Bill> getAllBill();
}
