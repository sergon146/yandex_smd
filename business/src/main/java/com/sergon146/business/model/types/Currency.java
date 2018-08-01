package com.sergon146.business.model.types;

public enum Currency {
    RUBLE("₽", "RUB"),
    DOLLAR("$", "USD"),
    YEN("¥", "YEN");

    private final String symbol;
    private final String shortName;

    Currency(String symbol, String shortName) {
        this.symbol = symbol;
        this.shortName = shortName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getShortName() {
        return shortName;
    }
}
