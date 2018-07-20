package com.sergon146.business.usecase;

import com.sergon146.business.contracts.BalanceUseCase;
import com.sergon146.business.model.Balance;
import com.sergon146.business.repository.BalanceRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public class BalanceUseCaseImpl implements BalanceUseCase {

    private final BalanceRepository balanceRepository;

    public BalanceUseCaseImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Observable<List<Balance>> getBalance() {
        return balanceRepository.getBalance();
    }
}