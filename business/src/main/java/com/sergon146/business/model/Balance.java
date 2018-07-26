package com.sergon146.business.model;

import com.sergon146.business.model.types.Currency;

public class Balance {
    private double amount;
    private Currency currency;

    public Balance(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getValue() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
