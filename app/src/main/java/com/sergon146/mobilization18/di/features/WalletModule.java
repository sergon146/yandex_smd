package com.sergon146.mobilization18.di.features;

import com.sergon146.business.contracts.WalletUseCase;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;
import com.sergon146.business.usecase.WalletUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.wallet.WalletPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class WalletModule {

    @Provides
    static WalletUseCase provideBalanceUseCase(BalanceRepository balanceRepository,
                                               TransactionRepository transactionRepository,
                                               WalletRepository walletRepository) {
        return new WalletUseCaseImpl(balanceRepository, transactionRepository, walletRepository);
    }

    @Provides
    static WalletPresenter provideBalancePresenter(MainRouter router,
                                                   WalletUseCase useCase) {
        return new WalletPresenter(router, useCase);
    }
}
