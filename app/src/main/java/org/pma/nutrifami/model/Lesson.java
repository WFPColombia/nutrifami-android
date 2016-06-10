package org.pma.nutrifami.model;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class Lesson {
    private String mId;
    private String mTitle;
    private String mImage;
    private String mDescription;
    private Unit[][] mUnits;

    public Lesson(String id, String title, String image, String description, Unit[][] units) {
        this.mId = id;
        this.mTitle = title;
        this.mImage = image;
        this.mDescription = description;
        this.mUnits = units;
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

    public String getDescription() {
        return mDescription;
    }

    public Unit[][] getUnits() {
        return mUnits;
    }
}
