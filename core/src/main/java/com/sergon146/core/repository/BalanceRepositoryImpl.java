package com.sergon146.core.repository;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.core.api.ApiService;

import java.util.ArrayList;
import java.util.List;

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
    public Observable<List<Balance>> getBalance() {

        double currentBalance = 12000.00;
        double exchangeRate = 63.60;

        List<Balance> balancesHardcode = new ArrayList<>();
        Balance rub = new Balance(currentBalance, Currency.RUBLE);
        Balance usd = new Balance(currentBalance / exchangeRate, Currency.DOLLAR);

        balancesHardcode.add(rub);
        balancesHardcode.add(usd);

        return Observable.just(balancesHardcode);
    }
}
