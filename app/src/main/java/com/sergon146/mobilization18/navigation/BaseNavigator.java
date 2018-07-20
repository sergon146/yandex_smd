package com.sergon146.mobilization18.navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.gson.Gson;

import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
public abstract class BaseNavigator extends SupportAppNavigator {

    public static final String TAG = BaseNavigator.class.getSimpleName();

    private final int containerId;

    public BaseNavigator(final FragmentActivity activity, final int containerId) {
        super(activity, containerId);
        this.containerId = containerId;
    }

    @Override
    public void applyCommand(final Command command) {
        Log.v(TAG, "applyCommand " + printCommand(command));
        super.applyCommand(command);
    }

    private String printCommand(Command command) {
        return new Gson().toJson(command);
    }

    @Override
    protected Intent createActivityIntent(final String screenKey, final Object data) {
        Log.v(TAG, "navigate to activity " + screenKey + " with data = "
                + new Gson().toJson(data));
        return null;
    }

    @Override
    protected Fragment createFragment(final String screenKey, final Object data) {
        Log.v(TAG, "navigate to fragment " + screenKey + " with data = "
                + new Gson().toJson(data));
        return null;
    }

    @Override
    protected void unknownScreen(Command command) {
        Log.e(TAG, "unknown screen " + printCommand(command));
    }

    public int getContainerId() {
        return containerId;
    }

    protected abstract Intent createOrApply(final String screenKey, final Object[] transitionData);
}
