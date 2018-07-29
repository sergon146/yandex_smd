package com.sergon146.business.contracts;


import com.sergon146.business.model.Transaction;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 10.04.2018
 */

public interface TransactionsUseCase {
    Observable<List<Transaction>> getTransactions();
}
