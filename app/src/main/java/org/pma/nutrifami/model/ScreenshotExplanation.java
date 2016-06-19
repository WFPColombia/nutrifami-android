package org.pma.nutrifami.model;

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

    public static ScreenshotExplanation getProfileExplanation() {
        return new ScreenshotExplanation(
                "PROFILE",
                "RECEIVE SHOPPING RECOMMENDATIONS BASED ON YOUR LAST PURCHASES",
                R.mipmap.perfil,
                false
        );
    }

    public static ScreenshotExplanation getTrainingExplanation() {
        return new ScreenshotExplanation(
                "TRAINING",
                "LEARN HOW TO EAT IN A FUN WAY",
                R.mipmap.modules,
                true
        );
    }
}
