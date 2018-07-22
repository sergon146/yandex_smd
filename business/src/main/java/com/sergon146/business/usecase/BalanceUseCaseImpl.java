package com.sergon146.business.usecase;

import com.sergon146.business.contracts.BalanceUseCase;
import com.sergon146.business.model.Balance;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.business.repository.TransactionRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public class BalanceUseCaseImpl implements BalanceUseCase {

    private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;

    public BalanceUseCaseImpl(BalanceRepository balanceRepository,
                              TransactionRepository transactionRepository) {
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Observable<List<Balance>> getBalance() {
        return balanceRepository.getBalance();
    }

    @Override
    public Observable<List<Transaction>> getTransactions() {
        return transactionRepository.getTransaction();
    }

    @Override
    public Observable<Long> getTransactionSumm() {
        return transactionRepository.getTransactionSum();
    }
}