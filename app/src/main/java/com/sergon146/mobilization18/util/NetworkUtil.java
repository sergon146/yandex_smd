package com.sergon146.mobilization18.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 29.04.2018
 */

public class NetworkUtil {

    /**
     * Checking is there are network connection
     *
     * @param context Context
     * @return is there a connection
     */
    public static boolean isLostConnection(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm == null) {
                return true;
            }

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
                    || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                    return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }
}
