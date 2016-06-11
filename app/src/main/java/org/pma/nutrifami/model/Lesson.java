package org.pma.nutrifami.model;

import org.pma.nutrifami.model.unit.Unit;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class Lesson {
    private final String mId;
    private final String mTitle;
    private final String mImage;
    private final String mDescription;
    private final Unit[][] mUnits;

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
