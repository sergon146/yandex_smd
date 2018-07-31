package com.sergon146.mobilization18.ui.base.dialog;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface BaseDialogMvpView extends MvpView {
    void setActionBarTitle(int stringId);

    void setActionBarTitle(String title);

    void showToast(int stringId);

    void showToast(String string);

}