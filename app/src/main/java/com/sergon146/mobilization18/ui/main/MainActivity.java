package com.sergon146.mobilization18.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.navigation.Screens;
import com.sergon146.mobilization18.ui.base.BaseMvpActivity;
import com.sergon146.mobilization18.ui.base.dialog.BaseDialogMvpFragment;
import com.sergon146.mobilization18.ui.fragments.about.AboutFragment;
import com.sergon146.mobilization18.ui.fragments.addtransaction.AddTransactionDialog;
import com.sergon146.mobilization18.ui.fragments.balance.BalanceFragment;
import com.sergon146.mobilization18.ui.fragments.report.ReportFragment;
import com.sergon146.mobilization18.ui.fragments.settings.SettingsFragment;
import com.sergon146.mobilization18.ui.fragments.transactions.TransactionsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainPresenter>
        implements MainView, FragmentManager.OnBackStackChangedListener {

    @Inject
    @InjectPresenter
    MainPresenter presenter;

    @Inject
    MainRouter router;

    @BindView(R.id.tab_bar)
    AHBottomNavigation tabBar;
    @BindView(R.id.back)
    View back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.add_fab)
    FloatingActionButton addFab;

    private FragmentManager fragmentManager;

    @Override
    @ProvidePresenter
    protected MainPresenter providePresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentManager.addOnBackStackChangedListener(this);
        shouldDisplayBack();

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

        FragmentManager fm = fragmentManager;
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
        tabBar.setAccentColor(ContextCompat.getColor(this, R.color.main_color));
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
    public void setActionBarTitle(String title) {
        this.title.setText(title);
    }

    @Override
    protected int getNavigationContainerId() {
        return R.id.container;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        Fragment fragment = createFragmentInternal(screenKey, data);
        if (fragment instanceof BaseDialogMvpFragment) {
            ((BaseDialogMvpFragment) fragment).show(fragmentManager, screenKey);
            return null;
        }
        return fragment;

    }

    private Fragment createFragmentInternal(String screenKey, Object data) {
        Screens screen = Screens.valueOf(screenKey);
        Fragment fragment;
        switch (screen) {
            case MAIN_SCREEN:
                fragment = BalanceFragment.newInstance();
                break;
            case FEED_SCREEN:
                fragment = TransactionsFragment.newInstance();
                break;
            case REPORT_SCREEN:
                fragment = ReportFragment.newInstance();
                break;
            case SETTINGS_SCREEN:
                fragment = SettingsFragment.newInstance();
                break;
            case ABOUT_SCREEN:
                fragment = AboutFragment.newInstance();
                break;
            case ADD_TRANSACTION:
                fragment = AddTransactionDialog.newInstance();
                break;
            default:
                throw new RuntimeException("Unknown screen");
        }
        return fragment;
    }


    @Override
    public void onBackStackChanged() {
        shouldDisplayBack();
    }

    public void shouldDisplayBack() {
        boolean canBack = fragmentManager.getBackStackEntryCount() > 1;
        back.setVisibility(canBack ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.back)
    void onBackClick() {
        getPresenter().goBack();
    }

    @Override
    public void onBackPressed() {
        int entryCount = fragmentManager.getBackStackEntryCount();
        if (entryCount <= 1) {
            finish();
        } else {
            super.onBackPressed();
            String name = fragmentManager.getBackStackEntryAt(entryCount - 1).getName();
            activateScreen(name);
        }
    }

    @OnClick(R.id.add_fab)
    void onAddTransactionClick() {
        getPresenter().showAddTransaction();
    }
}
