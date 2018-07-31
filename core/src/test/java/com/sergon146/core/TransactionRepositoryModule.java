package com.sergon146.core;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.core.api.ApiService;
import com.sergon146.core.repository.TransactionRepositoryImpl;

import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
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
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.INCOME,
                Currency.RUBLE, BigDecimal.valueOf(5000), BigDecimal.valueOf(1)));
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.INCOME,
                Currency.RUBLE, BigDecimal.valueOf(5000), BigDecimal.valueOf(1)));
        repo.getTransactionSum(transactions).test().assertValue(BigDecimal.valueOf(10000));

        //minus
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.EXPENSE,
                Currency.RUBLE, BigDecimal.valueOf(5000), BigDecimal.valueOf(1)));
        repo.getTransactionSum(transactions).test().assertValue(BigDecimal.valueOf(5000));


        //exchange plus
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.INCOME,
                Currency.DOLLAR, BigDecimal.valueOf(5000), BigDecimal.valueOf(0.5)));
        repo.getTransactionSum(transactions).test().assertValue(BigDecimal.valueOf(15000));

        //exchange plus
        transactions.add(new Transaction(UUID.randomUUID(), OperationType.EXPENSE,
                Currency.DOLLAR, BigDecimal.valueOf(5000), BigDecimal.valueOf(0.5)));
        repo.getTransactionSum(transactions).test().assertValue(BigDecimal.valueOf(5000));
    }
}