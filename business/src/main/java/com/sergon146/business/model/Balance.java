package com.sergon146.business.model;

import com.sergon146.business.model.types.Currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Balance {
    private BigDecimal amount;
    private Currency currency;
    private Map<Currency, BigDecimal> exchangeMap;

    public Balance(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
        exchangeMap = new HashMap<>();
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

    public Map<Currency, BigDecimal> getExchangeMap() {
        return exchangeMap;
    }

    public void setExchangeMap(Map<Currency, BigDecimal> exchangeMap) {
        this.exchangeMap = exchangeMap;
    }

    public void addExchange(Currency key, BigDecimal amount) {
        exchangeMap.put(key, amount);
    }
}
