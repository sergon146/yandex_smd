package com.sergon146.business.model;

import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.business.model.types.TransactionCategory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Transaction {
    private UUID uuid;
    private OperationType type;
    private Currency currency;
    private BigDecimal amount;
    private BigDecimal exchangeRate;
    private Date date;
    private TransactionCategory category = TransactionCategory.OTHER;
    private Wallet wallet;

    public Transaction(OperationType type,
                       Currency currency,
                       BigDecimal amount,
                       BigDecimal exchangeRate) {
        this.uuid = UUID.randomUUID();
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.date = new Date();
    }

    public Transaction(UUID uuid,
                       OperationType type,
                       Currency currency,
                       BigDecimal amount,
                       BigDecimal exchangeRate) {
        this.uuid = uuid;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.date = new Date();
    }


    public Transaction(UUID uuid,
                       OperationType type,
                       Currency currency,
                       BigDecimal amount,
                       BigDecimal exchangeRate,
                       Date date) {
        this.uuid = uuid;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.date = date;
    }

    public static Transaction getRandomTransaction() {
        Random random = new Random();
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                OperationType.values()[random.nextInt(OperationType.values().length)],
                Currency.values()[random.nextInt(Currency.values().length)],
                BigDecimal.valueOf(random.nextDouble() * 5000),
                BigDecimal.valueOf(random.nextDouble() * 60),
                new Date());
        transaction.setCategory(TransactionCategory.values()
                [random.nextInt(TransactionCategory.values().length)]);
        return transaction;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public Date getDate() {
        return date;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
