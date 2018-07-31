package com.sergon146.business.repository;

import com.sergon146.business.model.Wallet;

import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;

public interface WalletRepository {
    Observable<List<Wallet>> getWallets();

    Observable<Wallet> getWallet(UUID uuid);
}
