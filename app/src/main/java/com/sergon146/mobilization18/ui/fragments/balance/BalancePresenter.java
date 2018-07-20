package com.sergon146.mobilization18.ui.fragments.balance;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.BalanceUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

@InjectViewState
public class BalancePresenter extends BasePresenter<BalanceView> {
    private final BalanceUseCase useCase;

    public BalancePresenter(MainRouter router, BalanceUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected String getScreenTag() {
        return "BalancePresenter";
    }
}
