package com.sergon146.mobilization18.ui.fragments.about;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.SettingsUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

@InjectViewState
public class AboutPresenter extends BasePresenter<AboutView> {

    private final SettingsUseCase useCase;

    public AboutPresenter(MainRouter router, SettingsUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected String getScreenTag() {
        return "AboutPresenter";
    }
}
