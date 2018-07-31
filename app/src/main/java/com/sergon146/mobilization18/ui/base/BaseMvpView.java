package com.sergon146.mobilization18.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface BaseMvpView extends MvpView {
    void setActionBarTitle(int stringId);

    void setActionBarTitle(String title);

    void showToast(int stringId);

    void showToast(String string);

    void showConnectionError();

    void showLoadingError();

    void connectionLost();

    void connectionRestore();
}
