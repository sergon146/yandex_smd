package com.sergon146.business.model;

public enum Currency {
    RUBLE("рубль", "₽"),
    DOLLAR("доллар", "$");

    private final String title;
    private final String symbol;

    Currency(String title, String symbol) {
        this.title = title;
        this.symbol = symbol;
    }

    public String getTitle() {
        return title;
    }

    public String getSymbol() {
        return symbol;
    }
}
