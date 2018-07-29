package com.sergon146.mobilization18.ui.base.dialog;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class InjectableDialogFragment extends MvpAppCompatDialogFragment
        implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
