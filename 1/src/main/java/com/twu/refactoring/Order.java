package com.twu.refactoring;

import java.util.List;

public class Order {
    String customerName;
    String customerAddress;
    List<LineItem> listItem;

    public Order(String customerName, String customerAddress, List<LineItem> listItem) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.listItem = listItem;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<LineItem> getLineItems() {
        return listItem;
    }
}
