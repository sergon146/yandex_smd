package com.sergon146.core.repository;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.core.api.ApiService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class TransactionRepositoryImpl implements TransactionRepository {
    private final ApiService apiService;
    private final Currency currentCurrency = Currency.RUBLE;

    private List<Transaction> transactions;

    public TransactionRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;

        transactions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            transactions.add(Transaction.getRandomTransaction());
        }
    }

    @Override
    public Observable<List<Transaction>> getTransaction() {

        return Observable.just(transactions);
    }

    @Override
    public Observable<BigDecimal> getTransactionSum(List<Transaction> transactions) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            BigDecimal amount;
            if (currentCurrency.equals(transaction.getCurrency())) {
                amount = transaction.getAmount();
            } else {
                amount = transaction.getAmount().divide(transaction.getExchangeRate(),
                        BigDecimal.ROUND_HALF_UP);
            }

            if (transaction.getType() == OperationType.INCOME) {
                sum = sum.add(amount);
            } else {
                sum = sum.subtract(amount);
            }
        }
        return Observable.just(sum);
    }
}
