package org.pma.nutrifami.model;

/**
 * Created by Peter on 10.06.2016.
 */
public class IntroductionUnit extends Unit {
    private final String mTitle;
    private final String mDescription;

    public IntroductionUnit(String title, String description) {
        super(UnitType.Introduction, null, null, -1, null, null);
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
