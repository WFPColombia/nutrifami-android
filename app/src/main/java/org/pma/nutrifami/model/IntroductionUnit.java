package org.pma.nutrifami.model;

/**
 * Created by Peter on 10.06.2016.
 */
public class IntroductionUnit extends Unit {
    private String mTitle;
    private String mDescription;

    public IntroductionUnit(String title, String description) {
        this.mUnitType = UnitType.Introduction;

        this.mTitle = title;
        this.mDescription = description;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }
}
