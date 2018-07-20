package com.sergon146.business.model;

public enum Currency {
    RUBLE("рубль", "₽", "RUB"),
    DOLLAR("доллар", "$", "USD");

    private final String title;
    private final String symbol;
    private final String shortName;

    Currency(String title, String symbol, String shortName) {
        this.title = title;
        this.symbol = symbol;
        this.shortName = shortName;
    }

    public String getTitle() {
        return title;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getShortName() {
        return shortName;
    }
}
