package com.sergon146.mobilization18.di.features;

import com.sergon146.business.contracts.SettingsUseCase;
import com.sergon146.business.usecase.SettingsUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.about.AboutPresenter;
import com.sergon146.mobilization18.ui.fragments.settings.SettingsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AboutModule {

    @Provides
    static SettingsUseCase provideBalanceUseCase() {
        return new SettingsUseCaseImpl();
    }

    @Provides
    static AboutPresenter provideBalancePresenter(MainRouter router,
                                                  SettingsUseCase useCase) {
        return new AboutPresenter(router, useCase);
    }
}
