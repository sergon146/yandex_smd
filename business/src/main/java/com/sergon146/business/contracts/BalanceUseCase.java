package com.sergon146.business.contracts;


import com.sergon146.business.model.Balance;
import com.sergon146.business.model.Transaction;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public interface BalanceUseCase {
    Observable<List<Balance>> getBalance();

    Observable<List<Transaction>> getTransactions();

    Observable<Long> getTransactionSumm();
}
