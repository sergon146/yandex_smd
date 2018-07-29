package com.sergon146.core.repository;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.core.api.ApiService;

import java.math.BigDecimal;
import java.util.Random;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class BalanceRepositoryImpl implements BalanceRepository {

    private final ApiService apiService;

    public BalanceRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Balance> getBalance() {
        return Observable.just(getMockBalance());
    }

    private Balance getMockBalance() {
        Random random = new Random();
        Balance balance = new Balance(BigDecimal.valueOf(random.nextDouble() * 10000),
                Currency.RUBLE);
        balance.addExchange(Currency.DOLLAR, BigDecimal.valueOf(random.nextDouble() * 2000));
        return balance;
    }
}
