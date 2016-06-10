package org.pma.nutrifami.model;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class Module {
    private String mId;
    private String mTitle;
    private String mImage;
    private Lesson[] mLessons;

    public Module(String id, String title, String image, Lesson[] lessons) {
        this.mId = id;
        this.mTitle = title;
        this.mImage = image;
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


    public Lesson[] getLessons() {
        return mLessons;
    }
}
