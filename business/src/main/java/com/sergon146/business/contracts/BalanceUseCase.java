package com.sergon146.business.contracts;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Observable;

public interface BalanceUseCase {
    Observable<Balance> getBalance();

    Observable<List<Wallet>> getWallets();

    Observable<List<Transaction>> getTransactions();

    Observable<BigDecimal> getTransactionSum();

    Observable<ExchangeRate> getExchangeRate();
}
