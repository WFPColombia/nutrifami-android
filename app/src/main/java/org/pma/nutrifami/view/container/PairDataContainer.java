package org.pma.nutrifami.view.container;

/**
 * Created by juras on 11-Jun-16.
 */

public class PairDataContainer {
    private final String mPairPart;
    private PairPartState mPairPartState;

    public PairDataContainer(String pairPart) {
        this.mPairPart = pairPart;
        this.mPairPartState = PairPartState.Unselected;
    }

    public void setPairPartState(PairPartState pairPartState) {
        this.mPairPartState = pairPartState;
    }

    public String getPairPart() {
        return this.mPairPart;
    }

    public PairPartState getPairPartState() {
        return this.mPairPartState;
    }
}
