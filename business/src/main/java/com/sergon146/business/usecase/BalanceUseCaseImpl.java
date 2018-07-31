package com.sergon146.business.usecase;

import com.sergon146.business.contracts.BalanceUseCase;
import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.business.repository.ExchageRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

public class BalanceUseCaseImpl implements BalanceUseCase {

    private final BalanceRepository balanceRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final ExchageRepository exchageRepository;

    public BalanceUseCaseImpl(BalanceRepository balanceRepository,
                              WalletRepository walletRepository,
                              TransactionRepository transactionRepository,
                              ExchageRepository exchageRepository) {
        this.balanceRepository = balanceRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.exchageRepository = exchageRepository;
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

    @Override
    public Observable<ExchangeRate> getExchangeRate() {
        return exchageRepository.getExchangeRate("USD", "RUB");
    }
}