package com.sergon146.mobilization18.di.features;

import com.sergon146.business.contracts.BalanceUseCase;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.usecase.BalanceUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.balance.BalancePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class BalanceModule {

    @Provides
    static BalanceUseCase provideBalanceUseCase(BalanceRepository balanceRepository,
                                                TransactionRepository transactionRepository) {
        return new BalanceUseCaseImpl(balanceRepository, transactionRepository);
    }

    @Provides
    static BalancePresenter provideBalancePresenter(MainRouter router,
                                                    BalanceUseCase useCase) {
        return new BalancePresenter(router, useCase);
    }
}
