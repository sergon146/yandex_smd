package com.sergon146.mobilization18.di.base;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Sergon146 on 08.04.2018.
 * <sergon146@gmail.com>
 */

public abstract class InjectableActivity extends MvpAppCompatActivity
        implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
