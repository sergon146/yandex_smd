package com.sergon146.mobilization18.ui.fragments.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingsFragment extends BaseMvpFragment<SettingsPresenter>
        implements SettingsView {

    @Inject
    @InjectPresenter
    SettingsPresenter presenter;


    public static SettingsFragment getInstance() {
        return new SettingsFragment();
    }

    @Override
    @ProvidePresenter
    protected SettingsPresenter providePresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, root);
        setActionBarTitle(R.string.settings_title);
        return root;
    }

    @OnClick(R.id.about)
    void onAboutClick() {
        getPresenter().showAboutScreen();
    }

    @Override
    public String getLogName() {
        return "SettingsFragment";
    }
}
