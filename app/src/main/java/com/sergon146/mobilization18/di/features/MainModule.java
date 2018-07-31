package com.sergon146.mobilization18.di.features;

import com.sergon146.business.contracts.MainUseCase;
import com.sergon146.business.usecase.MainUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.about.AboutFragment;
import com.sergon146.mobilization18.ui.fragments.addtransaction.AddTransactionDialog;
import com.sergon146.mobilization18.ui.fragments.balance.BalanceFragment;
import com.sergon146.mobilization18.ui.fragments.report.ReportFragment;
import com.sergon146.mobilization18.ui.fragments.settings.SettingsFragment;
import com.sergon146.mobilization18.ui.fragments.transactions.TransactionsFragment;
import com.sergon146.mobilization18.ui.fragments.wallet.WalletFragment;
import com.sergon146.mobilization18.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {

    @Provides
    static MainUseCase provideMainUseCase() {
        return new MainUseCaseImpl();
    }

    @Provides
    static MainPresenter provideMainPresenter(MainRouter router, MainUseCase useCase) {
        return new MainPresenter(router, useCase);
    }

    @ContributesAndroidInjector(modules = BalanceModule.class)
    abstract BalanceFragment contributeBalanceFragment();

    @ContributesAndroidInjector(modules = SettingsModule.class)
    abstract SettingsFragment contributeSettingsFragment();

    @ContributesAndroidInjector(modules = AboutModule.class)
    abstract AboutFragment contributeAboutFragment();

    @ContributesAndroidInjector(modules = TransactionsModule.class)
    abstract TransactionsFragment contributeTransactionsFragment();

    @ContributesAndroidInjector(modules = WalletModule.class)
    abstract WalletFragment contributeWalletFragment();

    @ContributesAndroidInjector(modules = AddTransactionModule.class)
    abstract AddTransactionDialog contributeAddTransactionDialog();

    @ContributesAndroidInjector(modules = ReportModule.class)
    abstract ReportFragment contributeReportFragment();
}
