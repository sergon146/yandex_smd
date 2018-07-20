package com.sergon146.mobilization18.ui.main;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.MainUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 08.04.2018
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {
    private final MainUseCase usecase;

    public MainPresenter(MainRouter router, MainUseCase useCase) {
        super(router);
        this.usecase = useCase;
    }

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
    }

    @Override
    protected String getScreenTag() {
        return "MainPresenter";
    }
}
