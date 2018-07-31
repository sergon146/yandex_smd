package com.sergon146.mobilization18.ui.fragments.wallet;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.mobilization18.ui.base.BaseMvpView;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface WalletView extends BaseMvpView {
    void showWallet(Wallet wallet);

    void showTransactions(List<Transaction> transactions);
}
