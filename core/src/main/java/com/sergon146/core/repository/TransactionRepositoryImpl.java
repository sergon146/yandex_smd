package com.sergon146.core.repository;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.core.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class TransactionRepositoryImpl implements TransactionRepository {
    private final ApiService apiService;
    private final Currency currentCurrency = Currency.RUBLE;

    public TransactionRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<Transaction>> getTransaction() {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            transactions.add(Transaction.getRandomTransaction());
        }
        return Observable.just(transactions);
    }

    @Override
    public Observable<Long> getTransactionSum() {
        return getTransaction().map(item -> {
            long sum = 0;
            for (Transaction transaction : item) {
                if (currentCurrency.equals(transaction.getCurrency())) {
                    sum += transaction.getAmount();
                } else {
                    sum += transaction.getAmount() / transaction.getExchangeRate();
                }
            }
            return sum;
        });
    }
}
