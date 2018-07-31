package com.sergon146.business.usecase;

import com.sergon146.business.contracts.AddTransactionUseCase;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;

import java.util.List;

import io.reactivex.Observable;

public class AddTransactionUseCaseImpl implements AddTransactionUseCase {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public AddTransactionUseCaseImpl(WalletRepository walletRepository,
                                     TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Observable<List<Wallet>> getWallets() {
        return walletRepository.getWallets();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }
}
