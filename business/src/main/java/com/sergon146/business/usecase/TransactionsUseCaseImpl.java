package com.sergon146.business.usecase;

import com.sergon146.business.contracts.TransactionsUseCase;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 10.04.2018
 */

public class TransactionsUseCaseImpl implements TransactionsUseCase {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionsUseCaseImpl(TransactionRepository transactionRepository,
                                   WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public Observable<List<Transaction>> getTransactions() {
        return Observable.combineLatest(transactionRepository.getTransaction(),
                walletRepository.getWallets(), (transactions, wallets) -> {
                    Random random = new Random();
                    for (Transaction transaction : transactions) {
                        transaction.setWallet(wallets.get(random.nextInt(wallets.size())));
                    }
                    return transactions;
                });
    }
}
