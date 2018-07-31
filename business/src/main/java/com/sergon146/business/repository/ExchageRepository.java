package com.sergon146.business.repository;

import com.sergon146.business.model.ExchangeRate;

import io.reactivex.Observable;


public interface ExchageRepository {
    Observable<ExchangeRate> getExchangeRate(String in, String out);
}
