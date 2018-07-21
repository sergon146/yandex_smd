package com.sergon146.mobilization18.ui.fragments.settings;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.SettingsUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

@InjectViewState
public class SettingsPresenter extends BasePresenter<SettingsView> {

    private final SettingsUseCase useCase;

    public SettingsPresenter(MainRouter router, SettingsUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected String getScreenTag() {
        return "SettingsPresenter";
    }

    public void showAboutScreen() {
        getRouter().showAboutScreen();
    }
}
