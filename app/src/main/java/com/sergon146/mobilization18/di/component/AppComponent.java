package com.sergon146.mobilization18.di.component;

import android.app.Application;

import com.sergon146.mobilization18.App;
import com.sergon146.mobilization18.di.features.AppModule;
import com.sergon146.mobilization18.di.features.NavigationModule;
import com.sergon146.mobilization18.ui.base.BaseMvpActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, NavigationModule.class})
public interface AppComponent {
    void inject(BaseMvpActivity.NavigateInjector navigateInjector);

    void inject(App instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
