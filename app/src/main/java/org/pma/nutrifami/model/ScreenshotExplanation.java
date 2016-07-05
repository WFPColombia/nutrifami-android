package org.pma.nutrifami.model;

import android.content.Context;

import org.pma.nutrifami.R;

/**
 * Created by juras on 19-Jun-16.
 */

public class ScreenshotExplanation extends Explanation {
    private String mDescription;
    private int mScreenshot;
    private boolean mActionable;

    public ScreenshotExplanation(String title, String description, int screenshot, boolean actionable) {
        super(title);
        this.mDescription = description;
        this.mScreenshot = screenshot;
        this.mActionable = actionable;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getScreenshot() {
        return this.mScreenshot;
    }

    public boolean isActionable() {
        return this.mActionable;
    }

    public static ScreenshotExplanation getProfileExplanation(Context context) {
        return new ScreenshotExplanation(
                context.getString(R.string.explanation_profile_title),
                context.getString(R.string.explanation_profile_description),
                R.mipmap.perfil,
                false
        );
    }

    public static ScreenshotExplanation getTrainingExplanation(Context context) {
        return new ScreenshotExplanation(
                context.getString(R.string.explanation_training_title),
                context.getString(R.string.explanation_training_description),
                -1,
                true
        );
    }
}
