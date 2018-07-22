package com.sergon146.core;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.core.api.ApiService;
import com.sergon146.core.repository.TransactionRepositoryImpl;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TransactionRepositoryModule {

    @Test
    public void testTransactionSum() {
        ApiService service = Mockito.mock(ApiService.class);
        TransactionRepositoryImpl repo = new TransactionRepositoryImpl(service);
        List<Transaction> transactions = new ArrayList<>();

        //plus
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.INPUT,
                Currency.RUBLE, 5000, 1));
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.INPUT,
                Currency.RUBLE, 5000, 1));

        repo.getTransactionSum(transactions).test().assertValue(10000L);

        //minus
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.OUTPUT,
                Currency.RUBLE, 5000, 1));
        repo.getTransactionSum(transactions).test().assertValue(5000L);


        //exchange plus
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.INPUT,
                Currency.DOLLAR, 5000, 0.5));
        repo.getTransactionSum(transactions).test().assertValue(15000L);

        //exchange plus
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.OUTPUT,
                Currency.DOLLAR, 5000, 0.5));
        repo.getTransactionSum(transactions).test().assertValue(5000L);
    }
}