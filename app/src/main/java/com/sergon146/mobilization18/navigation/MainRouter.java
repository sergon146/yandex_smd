package com.sergon146.mobilization18.navigation;

import ru.terrakok.cicerone.BaseRouter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
public class MainRouter extends BaseRouter {
    private Navigator navigator;

    public void setNavigator(final Navigator navigator) {
        this.navigator = navigator;
    }

    public void showMainScreen() {
        if (navigator != null) {
            navigator.applyCommand(new Replace(Screens.MAIN_SCREEN.name(), null));
        }
    }

    public void back() {
        if (navigator != null) {
            navigator.applyCommand(new Back());
        }
    }

    public void navigateTo(String screenKey, String data) {
        if (navigator != null) {
            navigator.applyCommand(new Forward(screenKey, data));
        }
    }
}
