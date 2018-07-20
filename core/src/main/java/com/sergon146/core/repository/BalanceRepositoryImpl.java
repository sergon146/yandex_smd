package com.sergon146.core.repository;

import com.sergon146.business.model.Balance;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.core.api.ApiService;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class BalanceRepositoryImpl implements BalanceRepository {
    private static final int FIRST_PAGE = 1;

    private final ApiService apiService;

    public BalanceRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Balance> getBalance() {
        return null;
    }
}
