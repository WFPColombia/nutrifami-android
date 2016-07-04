package org.pma.nutrifami.lib;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.pma.nutrifami.R;

import java.util.Objects;

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

    public void showExplanation(Context context, String title, String content, int image, DialogInterface.OnDismissListener callback) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.explanation_custom_view, null, false);

        final ImageView imageView = (ImageView) view.findViewById(R.id.explanation_image);
        imageView.setImageResource(image);

        final TextView textView = (TextView) view.findViewById(R.id.explanation_text);
        textView.setText(content);

        final MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .customView(view, true)
                .dismissListener(callback)
                .positiveText(R.string.explanation_positive_text);
        if (title.equals(context.getString(R.string.explanation_correct))) {
            builder.titleColorRes(R.color.colorAccent);
        } else {
            builder.titleColorRes(R.color.colorRedSwipe);
        }
        builder.show();
    }
}
