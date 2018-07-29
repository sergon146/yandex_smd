package com.sergon146.mobilization18.ui.fragments.balance;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.sergon146.business.model.Balance;
import com.sergon146.business.model.Wallet;
import com.sergon146.mobilization18.ui.base.BaseMvpView;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface BalanceView extends BaseMvpView {
    void showBalance(Balance wallets);

    void showWallets(List<Wallet> wallets);
}
