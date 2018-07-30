package com.sergon146.mobilization18.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    public static class Ui {
        public static void hideKeyboard(Activity activity) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

            if (inputMethodManager == null) {
                return;
            }
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        public static void hideKeyboardFrom(Context context, View view) {
            InputMethodManager imm =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
