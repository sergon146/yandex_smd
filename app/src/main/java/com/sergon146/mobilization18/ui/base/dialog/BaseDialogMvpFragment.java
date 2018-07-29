package com.sergon146.mobilization18.ui.base.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.sergon146.mobilization18.ui.base.BaseMvpActivity;
import com.sergon146.mobilization18.ui.base.LogNamed;

public abstract class BaseDialogMvpFragment<Presenter extends BaseDialogPresenter>
        extends InjectableDialogFragment
        implements BaseDialogMvpView, LogNamed {

    protected static String LOG_TAG = "";

    Presenter presenter;

    protected Presenter providePresenter() {
        return null;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOG_TAG = this.getClass().getName();
        presenter = providePresenter();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        switch (style) {
            case STYLE_NO_TITLE:
                Window window = getDialog().getWindow();
                if (window != null) {
                    window.requestFeature(Window.FEATURE_NO_TITLE);
                }
                break;
            default:
        }
        super.setupDialog(dialog, style);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().hide();
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, e.getMessage());
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
}
