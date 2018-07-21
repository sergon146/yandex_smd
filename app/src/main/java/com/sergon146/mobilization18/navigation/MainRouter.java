package com.sergon146.mobilization18.navigation;

import ru.terrakok.cicerone.BaseRouter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;
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

    private void applyCommand(Command command) {
        if (navigator != null) {
            navigator.applyCommand(command);
        }
    }

    public void navigateTo(String screenKey, String data) {
        applyCommand(new Forward(screenKey, data));
    }

    public void back() {
        applyCommand(new Back());
    }

    public void showMainScreen() {
        applyCommand(new Replace(Screens.MAIN_SCREEN.name(), null));
    }

    public void showSettingsScreen() {
        applyCommand(new Forward(Screens.SETTINGS_SCREEN.name(), null));
    }

    public void showAboutScreen() {
        applyCommand(new Forward(Screens.ABOUT.name(), null));
    }
}
