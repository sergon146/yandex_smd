package com.sergon146.business.model;

public class Balance {
    private long count;
    private OperationType operationType;
    private Currency currency;

    public Balance(long count, OperationType operationType, Currency currency) {
        this.count = count;
        this.operationType = operationType;
        this.currency = currency;
    }

    public long getCount() {
        return count;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Currency getCurrency() {
        return currency;
    }
}
