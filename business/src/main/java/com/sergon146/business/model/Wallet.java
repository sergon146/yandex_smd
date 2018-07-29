package com.sergon146.business.model;

import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.WalletType;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {
    private UUID uuid = UUID.randomUUID();
    private BigDecimal amount;
    private Currency currency;
    private String name;
    private WalletType type = WalletType.CASH;

    public Wallet(BigDecimal amount, Currency currency, String name) {
        this.amount = amount;
        this.currency = currency;
        this.name = name;
    }

    public Wallet(BigDecimal amount, Currency currency, String name, WalletType type) {
        this.amount = amount;
        this.currency = currency;
        this.name = name;
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WalletType getType() {
        return type;
    }
}
