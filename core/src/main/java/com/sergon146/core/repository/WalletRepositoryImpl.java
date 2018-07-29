package com.sergon146.core.repository;

import com.sergon146.business.model.Wallet;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.WalletType;
import com.sergon146.business.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import io.reactivex.Observable;

public class WalletRepositoryImpl implements WalletRepository {
    private List<Wallet> wallets;

    public WalletRepositoryImpl() {
        wallets = getMockWallets();
    }

    @Override
    public Observable<List<Wallet>> getWallets() {
        return Observable.just(wallets);
    }

    @Override
    @Nullable
    public Observable<Wallet> getWallet(UUID uuid) {
        for (Wallet wallet : wallets) {
            if (wallet.getUuid().equals(uuid)) {
                return Observable.just(wallet);
            }
        }

        return null;
    }

    private List<Wallet> getMockWallets() {
        BigDecimal currentBalance = BigDecimal.valueOf(120036.00);
        BigDecimal exchangeRate = BigDecimal.valueOf(63.60);

        List<Wallet> wallets = new ArrayList<>();
        Wallet rub = new Wallet(currentBalance, Currency.RUBLE,
                "Наличка", WalletType.CASH);
        Wallet usd = new Wallet(currentBalance.divide(exchangeRate, 1), Currency.DOLLAR,
                "Сбер", WalletType.DEBIT_CARD);
        Wallet rub1 = new Wallet(currentBalance.divide(BigDecimal.valueOf(1.3), 1), Currency.RUBLE,
                "Клюква", WalletType.DEBIT_CARD);
        Wallet usd1 = new Wallet(currentBalance.multiply(exchangeRate), Currency.DOLLAR,
                "Сбербанк", WalletType.CREDIT_CARD);
        Wallet rub2 = new Wallet(currentBalance.divide(BigDecimal.valueOf(1.3), 1), Currency.RUBLE,
                "Клюква", WalletType.DEBIT_CARD);
        Wallet usd2 = new Wallet(currentBalance.multiply(exchangeRate), Currency.DOLLAR,
                "Сбербанк", WalletType.CREDIT_CARD);

        wallets.add(rub);
        wallets.add(usd);
        //wallets.add(rub1);
        //wallets.add(usd1);
        //wallets.add(rub2);
        //wallets.add(usd2);

        return wallets;
    }
}
