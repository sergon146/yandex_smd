package com.sergon146.mobilization18.ui.main;

import com.sergon146.mobilization18.R;

public enum TabBarScreens {
    MAIN(R.string.tab_main, R.drawable.ic_main_inactive),
    FEED(R.string.tab_feed, R.drawable.ic_bag_inactive),
    EMPTY(R.string.tab_transparent, R.drawable.ic_transparent),
    REPORT(R.string.tab_report, R.drawable.ic_report_inactive),
    PROFILE(R.string.tab_profile, R.drawable.ic_user_inactive);

    private int title;
    private int icon;

    TabBarScreens(int title, int icon) {
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
