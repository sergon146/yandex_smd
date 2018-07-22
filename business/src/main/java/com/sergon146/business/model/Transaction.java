package com.sergon146.business.model;

import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;

import java.util.Random;
import java.util.UUID;

public class Transaction {
    private UUID uuid;
    private OperationType type;
    private Currency currency;
    private double amount;
    private double exchangeRate;

    public static Transaction getRandomTransaction() {
        Random random = new Random();
        return new Transaction(
                UUID.randomUUID(),
                OperationType.values()[random.nextInt(OperationType.values().length)],
                Currency.values()[random.nextInt(Currency.values().length)],
                random.nextInt(5000),
                random.nextDouble() * 60);
    }

    public Transaction(UUID uuid,
                       OperationType type,
                       Currency currency,
                       double amount,
                       double exchangeRate) {
        this.uuid = uuid;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public OperationType getType() {
        return type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
