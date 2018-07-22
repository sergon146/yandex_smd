package com.sergon146.mobilization18.di.features;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.core.Core;
import com.sergon146.core.api.ApiService;
import com.sergon146.core.repository.BalanceRepositoryImpl;
import com.sergon146.core.repository.TransactionRepositoryImpl;
import com.sergon146.mobilization18.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 10.04.2018
 */

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Context provideContext(Application app) {
        return app;
    }

    @Singleton
    @Provides
    static Resources provideResources(Context context) {
        return context.getResources();
    }

    @Singleton
    @Provides
    static ApiService provideMiddlewareService() {
        return Core.api();
    }

    @Singleton
    @Provides
    static BalanceRepository provideBalanceRepository(ApiService apiService) {
        return new BalanceRepositoryImpl(apiService);
    }

    @Singleton
    @Provides
    static TransactionRepository provideTransactionRepository(ApiService apiService) {
        return new TransactionRepositoryImpl(apiService);
    }

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity contributeMainActivity();
}
