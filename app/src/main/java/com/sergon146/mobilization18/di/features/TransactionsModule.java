package com.sergon146.mobilization18.di.features;

import com.sergon146.business.contracts.TransactionsUseCase;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;
import com.sergon146.business.usecase.TransactionsUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.transactions.TransactionsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TransactionsModule {

    @Provides
    static TransactionsUseCase provideTransactionsUseCase(
            TransactionRepository transactionRepository,
            WalletRepository walletRepository) {
        return new TransactionsUseCaseImpl(transactionRepository, walletRepository);
    }

    @Provides
    static TransactionsPresenter provideTransactionsPresenter(MainRouter router,
                                                              TransactionsUseCase useCase) {
        return new TransactionsPresenter(router, useCase);
    }
}
