package com.sergon146.mobilization18.di.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.sergon146.mobilization18.App;
import com.sergon146.mobilization18.BuildConfig;
import com.sergon146.mobilization18.di.component.AppComponent;
import com.sergon146.mobilization18.di.component.DaggerAppComponent;
import com.sergon146.mobilization18.ui.base.LogNamed;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class AppInjector {
    private static final ScreenNavigateLogger logger = new ScreenNavigateLogger();

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static void init(App app) {
        appComponent = DaggerAppComponent.builder().application(app)
                .build();
        appComponent.inject(app);
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (BuildConfig.DEBUG && activity instanceof LogNamed) {
                    logger.onActivityOpened((LogNamed) activity);
                }
                handleActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


    private static void handleActivity(Activity activity) {
        if (activity instanceof HasSupportFragmentInjector) {
            AndroidInjection.inject(activity);
        }
        if (activity instanceof FragmentActivity) {
            ((FragmentActivity) activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(
                            new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentAttached(final FragmentManager fm, final Fragment
                                        f, final Context context) {
                                    if (BuildConfig.DEBUG && f instanceof LogNamed) {
                                        logger.onFragmentOpened((LogNamed) f);
                                    }
                                    if (f instanceof Injectable) {
                                        AndroidSupportInjection.inject(f);
                                    }
                                }

                                @Override
                                public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
                                    super.onFragmentDestroyed(fm, f);
                                    if (BuildConfig.DEBUG && f instanceof LogNamed) {
                                        logger.onFragmentDestroyed((LogNamed) f);
                                    }
                                }
                            }, true);
        }
    }

    public static class ScreenNavigateLogger {

        private static final String TAG = "screenLogger";

        void onFragmentOpened(LogNamed fragment) {
            Log.d(TAG, "fragment opened " + fragment.getLogName());
        }

        void onActivityOpened(LogNamed activity) {
            Log.d(TAG, "activity opened " + activity.getLogName());
        }

        void onFragmentDestroyed(LogNamed fragment) {
            Log.d(TAG, "fragment destroyed " + fragment.getLogName());
        }

    }
}
