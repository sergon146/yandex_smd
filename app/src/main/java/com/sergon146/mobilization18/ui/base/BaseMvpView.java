package com.sergon146.mobilization18.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Sergon146 on 08.04.2018.
 * <sergon146@gmail.com>
 */
@StateStrategyType(SkipStrategy.class)
public interface BaseMvpView extends MvpView {
    void showToast(int stringId);

    void showToast(String string);

    void showConnectionError();

    void showLoadingError();

    void connectionLost();

    void connectionRestore();
}
