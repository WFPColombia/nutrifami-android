package org.pma.nutrifami.model;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class Module {
    private String mId;
    private String mTitle;
    private String mImage;
    private String[] mProgress;
    private Lesson[] mLessons;

    public Module(String id, String title, String image, String[] progress, Lesson[] lessons) {
        this.mId = id;
        this.mTitle = title;
        this.mImage = image;
        this.mProgress = progress;
        this.mLessons = lessons;
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

    public String[] getProgress() {
        return mProgress;
    }

    public Lesson[] getLessons() {
        return mLessons;
    }
}
