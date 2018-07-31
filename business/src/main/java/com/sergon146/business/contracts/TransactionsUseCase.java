package com.sergon146.business.contracts;


import com.sergon146.business.model.Transaction;

import java.util.List;

import io.reactivex.Observable;

public interface TransactionsUseCase {
    Observable<List<Transaction>> getTransactions();
}
