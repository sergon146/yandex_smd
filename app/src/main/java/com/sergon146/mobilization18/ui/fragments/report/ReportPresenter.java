package com.sergon146.mobilization18.ui.fragments.report;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.ReportUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

@InjectViewState
public class ReportPresenter extends BasePresenter<ReportView> {
    private final ReportUseCase reportUseCase;

    public ReportPresenter(MainRouter router, ReportUseCase reportUseCase) {
        super(router);
        this.reportUseCase = reportUseCase;
    }

    @Override
    protected String getScreenTag() {
        return null;
    }
}
