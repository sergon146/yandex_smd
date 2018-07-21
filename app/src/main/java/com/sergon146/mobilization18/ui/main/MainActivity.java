package com.sergon146.mobilization18.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.navigation.Screens;
import com.sergon146.mobilization18.ui.base.BaseMvpActivity;
import com.sergon146.mobilization18.ui.fragments.balance.BalanceFragment;
import com.sergon146.mobilization18.ui.fragments.settings.SettingsFragment;

import javax.inject.Inject;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 08.04.2018
 */

public class MainActivity extends BaseMvpActivity<MainPresenter>
        implements MainView, FragmentManager.OnBackStackChangedListener {

    @Inject
    MainRouter router;

    @Inject
    @InjectPresenter
    MainPresenter presenter;

    @Override
    @ProvidePresenter
    protected MainPresenter providePresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            router.showMainScreen();
        }

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        shouldDisplayHomeUp();
    }

    @Override
    protected int getNavigationContainerId() {
        return R.id.container;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        Screens screen = Screens.valueOf(screenKey);
        switch (screen) {
            case MAIN_SCREEN:
                return BalanceFragment.getInstance();
            case SETTINGS_SCREEN:
                return SettingsFragment.getInstance();
            case ABOUT:
                //todo add about screen
                return null;
            default:
                throw new RuntimeException("Unknown screen");
        }
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 0;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(canback);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
