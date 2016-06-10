package org.pma.nutrifami.lib;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Peter Juras on 10.06.16.
 */

public class SessionManager {
    private static SessionManager mInstance;
    private static String SHARED_PREFERENCES_NAME = "Nutrifami.config";
    private static String FIRST_LAUNCH = "FIRST_LAUNCH";

    public static SessionManager getInstance() {
        if (mInstance == null) {
            mInstance = new SessionManager();
        }
        return mInstance;
    }

    private SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public boolean isFirstLaunch(Context context) {
        return getPreferences(context).getBoolean(FIRST_LAUNCH, true);
    }

    public void setFirstLaunch(Context context, boolean firstLaunch) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(FIRST_LAUNCH, firstLaunch);
        editor.commit();
    }
}
