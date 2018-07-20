package com.sergon146.mobilization18.ui.fragments.balance;

import com.sergon146.business.model.Balance;
import com.sergon146.mobilization18.ui.base.BaseMvpView;

import java.util.List;

public interface BalanceView extends BaseMvpView {
    void showBalance(List<Balance> balances);
}
