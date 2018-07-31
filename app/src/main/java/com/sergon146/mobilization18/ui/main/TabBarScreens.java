package com.sergon146.mobilization18.ui.main;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.sergon146.mobilization18.R;

public enum TabBarScreens {
    MAIN(R.string.tab_main, R.drawable.ic_main_inactive),
    FEED(R.string.tab_feed, R.drawable.ic_bag_inactive),
    EMPTY(R.string.tab_transparent, R.drawable.ic_transparent),
    REPORT(R.string.tab_report, R.drawable.ic_report_inactive),
    SETTINGS(R.string.tab_settings, R.drawable.ic_settings);

    @StringRes
    private int title;
    @DrawableRes
    private int icon;

    TabBarScreens(@StringRes int title, @DrawableRes int icon) {
        this.title = title;
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }
}
