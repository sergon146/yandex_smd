package com.sergon146.mobilization18.di.features;

import com.sergon146.business.contracts.ReportUseCase;
import com.sergon146.business.usecase.ReportUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.report.ReportPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ReportModule {

    @Provides
    static ReportUseCase provideReportUseCase() {
        return new ReportUseCaseImpl();
    }

    @Provides
    static ReportPresenter provideReportPresenter(MainRouter router,
                                                  ReportUseCase useCase) {
        return new ReportPresenter(router, useCase);
    }
}