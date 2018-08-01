package com.sergon146.mobilization18.ui.fragments.balance;

import android.util.Log;

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
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        bind(onUi(useCase.getBalance()).subscribe(bal ->
                getViewState().showBalance(bal)));

        bind(onUi(useCase.getWallets()).subscribe(wallets ->
                getViewState().showWallets(wallets)));

        getExchangeRate();
    }

    public void showSettings() {
        getRouter().showSettingsScreen();
    }

    public void getExchangeRate() {
        bind(onUi(useCase.getExchangeRate()).subscribe(rate -> {
                    String exchanger = rate.getIn() + " - " + rate.getOut() + " = " +
                            rate.getExchageRate().toPlainString();
                    Log.i(getScreenTag(), exchanger);
                },
                t -> Log.e(getScreenTag(), t.getMessage())));
    }

    @Override
    protected String getScreenTag() {
        return "BalancePresenter";
    }
}
