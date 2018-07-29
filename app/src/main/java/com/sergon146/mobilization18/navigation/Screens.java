package com.sergon146.mobilization18.navigation;

import com.sergon146.mobilization18.ui.main.TabBarScreens;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public enum Screens {
    MAIN_SCREEN,
    FEED_SCREEN(TabBarScreens.FEED),
    REPORT_SCREEN(TabBarScreens.REPORT),
    PROFILE_SCREEN(TabBarScreens.PROFILE),
    SETTINGS_SCREEN,
    ADD_TRANSACTION,
    ABOUT_SCREEN;

    Screens(TabBarScreens tab) {
        this.tab = tab;
    }

    Screens() {
        this(TabBarScreens.MAIN);
    }

    public static boolean contains(String value) {
        return getEnum(value) != null;
    }

    public static Screens getEnum(String value) {
        for (Screens c : Screens.values()) {
            if (c.name().equals(value)) {
                return c;
            }
        }

        return null;
    }

    public final TabBarScreens tab;
}
