package com.sergon146.mobilization18.di.features;

import com.sergon146.mobilization18.navigation.MainRouter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@Module
public class NavigationModule {
    private Cicerone<MainRouter> cicerone;

    public NavigationModule() {
        cicerone = Cicerone.create(new MainRouter());
    }

    @Provides
    @Singleton
    MainRouter provideRouter() {
        return cicerone.getRouter();
    }

    @Provides
    @Singleton
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }
}
