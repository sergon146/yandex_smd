package com.sergon146.business.repository;

import com.sergon146.business.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Observable;

public interface TransactionRepository {
    Observable<List<Transaction>> getTransaction();

    Observable<BigDecimal> getTransactionSum(List<Transaction> transactions);

    void addTransaction(Transaction transaction);
}
