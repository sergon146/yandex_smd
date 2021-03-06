package com.sergon146.mobilization18.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.base.Injectable;
import com.sergon146.mobilization18.di.base.InjectableFragment;

public abstract class BaseMvpFragment<Presenter extends BasePresenter> extends InjectableFragment
        implements BaseMvpView, Injectable, LogNamed {

    private Presenter presenter;


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            presenter.onReceive(context, intent);
        }
    };

    protected Presenter providePresenter() {
        return null;
    }

    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = providePresenter();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(receiver, filter);
    }

    @Override
    public void setActionBarTitle(int stringId) {
        setActionBarTitle(getString(stringId));
    }

    @Override
    public void setActionBarTitle(String title) {
        BaseMvpActivity activity = (BaseMvpActivity) getActivity();
        if (activity != null) {
            activity.setActionBarTitle(title);
        }
    }

    @Override
    public void showToast(int stringId) {
        Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showConnectionError() {
    }

    @Override
    public void showLoadingError() {
        Toast.makeText(getContext(), R.string.loading_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectionLost() {

    }

    @Override
    public void connectionRestore() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
