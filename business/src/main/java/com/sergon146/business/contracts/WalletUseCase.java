package com.sergon146.business.contracts;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface WalletUseCase {
    Observable<Wallet> getWallet(UUID uuid);

    Observable<List<Transaction>> getWalletTransactions(UUID uuid);
}
