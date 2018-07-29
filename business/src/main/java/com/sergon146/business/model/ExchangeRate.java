package com.sergon146.business.model;

import java.math.BigDecimal;

public class ExchangeRate {
    String in;
    String out;
    BigDecimal exchageRate;

    public ExchangeRate(String in, String out, String outPut) {
        this.in = in;
        this.out = out;
        String val = outPut.substring(outPut.lastIndexOf(':') + 1, outPut.length() - 1);
        this.exchageRate = BigDecimal.valueOf(Double.valueOf(val));
    }
    ////{"USD_RUB":62.7771}

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public BigDecimal getExchageRate() {
        return exchageRate;
    }
}
