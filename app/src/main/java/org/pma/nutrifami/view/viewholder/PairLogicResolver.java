package org.pma.nutrifami.view.viewholder;

import org.pma.nutrifami.model.unit.PairUnit;
import org.pma.nutrifami.view.adapter.PairDataAdapter;
import org.pma.nutrifami.view.container.PairDataContainer;
import org.pma.nutrifami.view.container.PairPartState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by juras on 12-Jun-16.
 */

public class PairLogicResolver {
    private int mLeftSelectedIndex;
    private String mLeftSelectedPart;
    private int mRightSelectedIndex;
    private String mRightSelectedPart;
    private int mLeftPartIndex;
    private int mRightPartIndex;

    private PairDataContainer[] mLeftPairData;
    private PairDataContainer[] mRightPairData;
    private Map<String, Integer> mAnswerMap;

    public PairLogicResolver() {
        this.mLeftSelectedIndex = -1;
        this.mLeftSelectedPart = null;
        this.mRightSelectedIndex = -1;
        this.mRightSelectedPart = null;
        this.mLeftPartIndex = -1;
        this.mRightPartIndex = -1;
    }

    public PairDataContainer[][] createPairDataContainers(PairUnit currentUnit) {
        final String[][] pairs = currentUnit.getPairs();
        final String[] leftPairParts = new String[pairs.length];
        final String[] rightPairParts = new String[pairs.length];

        // Shuffle pairs
        final ArrayList<String> leftPairList = new ArrayList<>();
        final ArrayList<String> rightPairList = new ArrayList<>();
        for (String[] pair : pairs) {
            leftPairList.add(pair[0]);
            rightPairList.add(pair[1]);
        }
        Collections.shuffle(leftPairList);
        Collections.shuffle(rightPairList);

        this.mAnswerMap = new HashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            final String leftPart = leftPairList.get(i);
            final String rightPart = rightPairList.get(i);

            this.mAnswerMap.put(pairs[i][0], i);
            this.mAnswerMap.put(pairs[i][1], i);
            leftPairParts[i] = leftPart;
            rightPairParts[i] = rightPart;
        }

        PairDataContainer[][] pairDataContainers = new PairDataContainer[2][];
        pairDataContainers[0] = new PairDataContainer[leftPairParts.length];
        pairDataContainers[1] = new PairDataContainer[rightPairParts.length];

        for (int i = 0; i < leftPairParts.length; i++) {
            pairDataContainers[0][i] = new PairDataContainer(leftPairParts[i]);
            pairDataContainers[1][i] = new PairDataContainer(rightPairParts[i]);
        }

        return pairDataContainers;
    }

    public void switchPartState(String pairPart, PairDataContainer[] leftPairData, PairDataContainer[] rightPairData, Map<String, Integer> answerMap) {
        this.mLeftPairData = leftPairData;
        this.mRightPairData = rightPairData;
        this.mAnswerMap = answerMap;

        PairPartState currentState = getCurrentState(pairPart);
        switch (currentState) {
            case Incorrect:
            case Unselected:
                selectPart(pairPart);
                break;
            case Selected:
                unselectCurrentPart();
                break;
        }
    }


    public boolean areAllCorrect(PairDataAdapter pairDataAdapter) {
        PairDataContainer[] dataContainers = pairDataAdapter.getDataContainers();
        for (PairDataContainer pairDataContainer : dataContainers) {
            if (pairDataContainer.getPairPartState() != PairPartState.Correct) {
                return false;
            }
        }
        return true;
    }

    private void enableSelectableFields(PairDataContainer[]... containers) {
        for (PairDataContainer[] pairDataContainers : containers) {
            for (PairDataContainer pairDataContainer : pairDataContainers) {
                if (pairDataContainer.getPairPartState() == PairPartState.Disabled) {
                    pairDataContainer.setPairPartState(PairPartState.Unselected);
                }
            }
        }
    }

    private void disableNonSelectedFields(PairDataContainer[] pairDataContainers) {
        for (PairDataContainer pairDataContainer : pairDataContainers) {
            final PairPartState state = pairDataContainer.getPairPartState();
            if (state == PairPartState.Unselected || state == PairPartState.Incorrect) {
                pairDataContainer.setPairPartState(PairPartState.Disabled);
            }
        }
    }

    private PairPartState getCurrentState(String pairPart) {
        PairPartState currentState = null;
        for (int i = 0; i < this.mLeftPairData.length; i++) {
            final PairDataContainer leftContainer = this.mLeftPairData[i];
            final PairDataContainer rightContainer = this.mRightPairData[i];
            final PairPartState leftPairState = leftContainer.getPairPartState();
            final PairPartState rightPairState = rightContainer.getPairPartState();

            if (leftPairState == PairPartState.Selected) {
                this.mLeftSelectedIndex = i;
                this.mLeftSelectedPart = leftContainer.getPairPart();
            } else if (leftPairState == PairPartState.Incorrect) {
                leftContainer.setPairPartState(PairPartState.Unselected);
            }
            if (rightPairState == PairPartState.Selected) {
                this.mRightSelectedIndex = i;
                this.mRightSelectedPart = rightContainer.getPairPart();
            } else if (rightPairState == PairPartState.Incorrect) {
                rightContainer.setPairPartState(PairPartState.Unselected);
            }
            if (leftContainer.getPairPart().equals(pairPart)) {
                currentState = leftPairState;
                this.mLeftPartIndex = i;
            } else if (rightContainer.getPairPart().equals(pairPart)) {
                currentState = rightPairState;
                this.mRightPartIndex = i;
            }
        }
        return currentState;
    }

    private void selectPart(String pairPart) {
        PairPartState newState;
        // Check if two pair parts are selected
        final int currentPartIndex = this.mAnswerMap.get(pairPart);
        if (this.mLeftPartIndex >= 0 && this.mRightSelectedIndex >= 0) {
            // Two things are selected, lets check if they are correct
            if (currentPartIndex == this.mAnswerMap.get(this.mRightSelectedPart)) {
                newState = PairPartState.Correct;
            } else {
                newState = PairPartState.Incorrect;
            }
            this.mLeftPairData[this.mLeftPartIndex].setPairPartState(newState);
            this.mRightPairData[this.mRightSelectedIndex].setPairPartState(newState);
            enableSelectableFields(this.mLeftPairData, this.mRightPairData);
        } else if (this.mRightPartIndex >= 0 && this.mLeftSelectedIndex >= 0) {
            if (currentPartIndex == this.mAnswerMap.get(this.mLeftSelectedPart)) {
                newState = PairPartState.Correct;
            } else {
                newState = PairPartState.Incorrect;
            }
            this.mLeftPairData[this.mLeftSelectedIndex].setPairPartState(newState);
            this.mRightPairData[this.mRightPartIndex].setPairPartState(newState);
            enableSelectableFields(this.mLeftPairData, this.mRightPairData);
        } else {
            // Only this part is selected now
            if (this.mLeftPartIndex >= 0) {
                this.mLeftPairData[this.mLeftPartIndex].setPairPartState(PairPartState.Selected);
                disableNonSelectedFields(this.mLeftPairData);
            } else {
                this.mRightPairData[this.mRightPartIndex].setPairPartState(PairPartState.Selected);
                disableNonSelectedFields(this.mRightPairData);
            }
        }
    }

    private void unselectCurrentPart() {
        // Unselect the current part
        if (this.mLeftPartIndex >= 0) {
            this.mLeftPairData[this.mLeftPartIndex].setPairPartState(PairPartState.Unselected);
            enableSelectableFields(this.mLeftPairData);
        } else {
            this.mRightPairData[this.mRightPartIndex].setPairPartState(PairPartState.Unselected);
            enableSelectableFields(this.mRightPairData);
        }
    }

    public Map<String, Integer> getAnswerMap() {
        return this.mAnswerMap;
    }
}
