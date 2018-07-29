package com.sergon146.business.usecase;

import com.sergon146.business.contracts.BalanceUseCase;
import com.sergon146.business.model.Balance;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public class BalanceUseCaseImpl implements BalanceUseCase {

    private final BalanceRepository balanceRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public BalanceUseCaseImpl(BalanceRepository balanceRepository,
                              WalletRepository walletRepository,
                              TransactionRepository transactionRepository) {
        this.balanceRepository = balanceRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Observable<Balance> getBalance() {
        return balanceRepository.getBalance();
    }

    @Override
    public Observable<List<Wallet>> getWallets() {
        return walletRepository.getWallets();
    }

    @Override
    public Observable<List<Transaction>> getTransactions() {
        return transactionRepository.getTransaction();
    }

    @Override
    public Observable<BigDecimal> getTransactionSum() {
        return transactionRepository.getTransactionSum(Collections.<Transaction>emptyList());
    }
}