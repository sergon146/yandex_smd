package com.sergon146.business.model;

public class Balance {
    private double count;
    private Currency currency;

    public Balance(double count, Currency currency) {
        this.count = count;
        this.currency = currency;
    }

    public double getValue() {
        return count;
    }

    public Currency getCurrency() {
        return currency;
    }
}
