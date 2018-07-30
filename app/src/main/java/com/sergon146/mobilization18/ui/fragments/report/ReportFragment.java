package com.sergon146.mobilization18.ui.fragments.report;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.base.Injectable;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ReportFragment extends BaseMvpFragment<ReportPresenter>
        implements ReportView, Injectable {

    @Inject
    @InjectPresenter
    ReportPresenter presenter;

    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Override
    @ProvidePresenter
    protected ReportPresenter providePresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_report, container, false);
        ButterKnife.bind(this, root);
        setActionBarTitle(R.string.report_title);
        return root;
    }

    @Override
    public String getLogName() {
        return "ReportFragment";
    }
}
