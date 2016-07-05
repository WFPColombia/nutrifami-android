package org.pma.nutrifami.model.unit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by juras on 11-Jun-16.
 */

public class PairUnit extends Unit {
    @SerializedName("pairs")
    private String[][] mPairs;

    public PairUnit(String[][] pairs, String answerExplanation, String voiceUri) {
        super(UnitType.Pairs, null, null, -1, answerExplanation, voiceUri);
        this.mPairs = pairs;
    }

    public String[][] getPairs() {
        return this.mPairs;
    }
}
