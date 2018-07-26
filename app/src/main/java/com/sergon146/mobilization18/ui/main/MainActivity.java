package com.sergon146.mobilization18.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.navigation.Screens;
import com.sergon146.mobilization18.ui.base.BaseMvpActivity;
import com.sergon146.mobilization18.ui.fragments.about.AboutFragment;
import com.sergon146.mobilization18.ui.fragments.balance.BalanceFragment;
import com.sergon146.mobilization18.ui.fragments.settings.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 08.04.2018
 */

public class MainActivity extends BaseMvpActivity<MainPresenter>
        implements MainView, FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.tab_bar)
    AHBottomNavigation tabBar;

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
        ButterKnife.bind(this);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        shouldDisplayHomeUp();

        if (savedInstanceState == null) {
            showInitialScreen();
        }

        prepareTabBar();
    }

    private void showInitialScreen() {
        getPresenter().showInitialScreen();
    }


    @Override
    protected void activateScreen(String name) {
        super.activateScreen(name);

        FragmentManager fm = getSupportFragmentManager();
        fm.executePendingTransactions();

        // display only root screen in tab
        Screens screen = Screens.getEnum(fm.getBackStackEntryAt(0).getName());
        if (screen != null) {
            tabBar.postDelayed(() -> activateTab(screen), 100);
        }
    }

    private void prepareTabBar() {

        List<AHBottomNavigationItem> items = new ArrayList<>();

        for (TabBarScreens screen : TabBarScreens.values()) {
            items.add(new AHBottomNavigationItem(getString(screen.getTitle()), screen.getIcon()));
        }

        tabBar.addItems(items);
        tabBar.setBehaviorTranslationEnabled(false);
        tabBar.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        tabBar.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.slate));
        tabBar.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tabBar.setInactiveColor(ContextCompat.getColor(this, R.color.white_50));
        tabBar.setNotificationBackgroundColorResource(R.color.rosy_pink);
        tabBar.setOnTabSelectedListener((position, wasSelected) -> {
            getPresenter().onTabClicked(position, wasSelected);
            return false;
        });

        activateTab(Screens.MAIN_SCREEN);
    }

    private void activateTab(Screens screen) {
        TabBarScreens tab = screen.tab;
        int pos = tab.ordinal();
        tabBar.setCurrentItem(pos, false);
        tabBar.invalidate();
    }

    @Override
    protected int getNavigationContainerId() {
        return R.id.container;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        Screens screen = Screens.valueOf(screenKey);
        Fragment fragment;
        switch (screen) {
            case MAIN_SCREEN:
                fragment = BalanceFragment.getInstance();
                break;
            case FEED_SCREEN:
                fragment = BalanceFragment.getInstance();
                break;
            case REPORT_SCREEN:
                fragment = BalanceFragment.getInstance();
                break;
            case PROFILE_SCREEN:
                fragment = BalanceFragment.getInstance();
                break;
            case SETTINGS_SCREEN:
                fragment = SettingsFragment.getInstance();
                break;
            case ABOUT_SCREEN:
                fragment = AboutFragment.getInstance();
                break;
            default:
                throw new RuntimeException("Unknown screen");
        }
        return fragment;
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 1;
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

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
            int count = getSupportFragmentManager().getBackStackEntryCount();
            String name = getSupportFragmentManager().getBackStackEntryAt(count - 1).getName();
            activateScreen(name);
        }
    }
}
