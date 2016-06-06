package org.pma.nutrifami.lib;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Peter Juras on 06.06.16.
 */

public class UnitExplanationManager {
    private static UnitExplanationManager mInstance;
    public static UnitExplanationManager getInstance() {
        if (mInstance == null) {
            mInstance = new UnitExplanationManager();
        }
        return mInstance;
    }

    public void showExplanation(Context context, String title, String content, DialogInterface.OnDismissListener callback) {
        new MaterialDialog.Builder(context)
            .title(title)
            .content(content)
            .dismissListener(callback)
            .positiveText("Ok")
            .show();
    }
}
