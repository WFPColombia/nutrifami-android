package org.pma.nutrifami.model;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class Module {
    private final String mId;
    private final String mTitle;
    private final String mImage;
    private final Lesson[] mLessons;

    // The prototype will only have one module, the others will simply be decoration. Therefore
    // we introduce a enabled field for modules. The real app should then decide whether all modules
    // are accessible from the beginning, or based on progress.
    private final boolean mEnabled;

    public Module(String id, String title, String image, Lesson[] lessons, boolean enabled) {
        this.mId = id;
        this.mTitle = title;
        this.mImage = image;
        this.mLessons = lessons;
        this.mEnabled = enabled;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImage() {
        return mImage;
    }

    public Lesson[] getLessons() {
        return mLessons;
    }

    public boolean getEnabled() {
        return this.mEnabled;
    }
}
