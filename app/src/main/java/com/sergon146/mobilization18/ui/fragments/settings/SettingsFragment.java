package com.sergon146.mobilization18.ui.fragments.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.core.utils.Const;
import com.sergon146.mobilization18.BuildConfig;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.util.Utils;

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

    @OnClick(R.id.feedback)
    void onFeedBackClick() {
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType(Const.EMAIL_TYPE);
        mailIntent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.developer_email));
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_title));
        String body = "Device: " + Utils.getDeviceName() + "\n" +
                "App version: " + BuildConfig.VERSION_NAME + "\n";
        mailIntent.putExtra(Intent.EXTRA_TEXT, body);
        try {
            startActivity(Intent.createChooser(mailIntent, getString(R.string.email_sending)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), getString(R.string.mail_apps_not_available),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getLogName() {
        return "SettingsFragment";
    }
}
