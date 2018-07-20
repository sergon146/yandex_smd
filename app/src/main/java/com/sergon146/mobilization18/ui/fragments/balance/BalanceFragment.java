package com.sergon146.mobilization18.ui.fragments.balance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.base.Injectable;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;

import javax.inject.Inject;

public class BalanceFragment extends BaseMvpFragment<BalancePresenter>
        implements BalanceView, Injectable {

    @Inject
    @InjectPresenter
    BalancePresenter presenter;


    public static BalanceFragment getInstance() {
        return new BalanceFragment();
    }

    @Override
    @ProvidePresenter
    protected BalancePresenter providePresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public String getLogName() {
        return "BalanceFragment";
    }
}
