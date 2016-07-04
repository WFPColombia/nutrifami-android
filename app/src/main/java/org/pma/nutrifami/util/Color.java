package org.pma.nutrifami.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

import org.pma.nutrifami.R;

/**
 * Created by juras on 13-Jun-16.
 */

public class Color {
    public static int getAccentColor(Context context) {
//        TypedValue typedValue = new TypedValue();
//
//        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorAccent});
//        int color = a.getColor(0, 0);

        return android.graphics.Color.argb(255, 50, 131, 41);
//        return android.graphics.Color.argb(255, 73, 187, 59);

//        a.recycle();
//        return color;
    }
}
