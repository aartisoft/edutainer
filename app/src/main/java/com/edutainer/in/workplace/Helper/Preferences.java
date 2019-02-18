package com.edutainer.in.workplace.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private Context context;
    private SharedPreferences sf;

    public Preferences(Context context) {
        this.context = context;
        sf = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
