package org.pma.nutrifami.adapter;

import org.pma.nutrifami.model.Unit;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeCardData {
    private Unit mUnit;
    private SwipeCardType mCardType;

    public SwipeCardData(Unit unit, SwipeCardType cardType) {
        this.mUnit = unit;
        this.mCardType = cardType;
    }

    public SwipeCardType getCardType() {
        return mCardType;
    }

    public Unit getUnit() {
        return mUnit;
    }
}
