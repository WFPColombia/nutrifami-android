package org.pma.nutrifami.view.viewholder;

import org.pma.nutrifami.model.unit.PairUnit;
import org.pma.nutrifami.view.adapter.PairDataAdapter;
import org.pma.nutrifami.view.container.PairDataContainer;
import org.pma.nutrifami.view.container.PairPartState;
import org.pma.nutrifami.view.listener.PairClickListener;

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

    private PairDataAdapter mLeftDataAdapter;
    private PairDataAdapter mRightDataAdapter;
    private Map<String, Integer> mAnswerMap;

    public PairLogicResolver() {
        this.mLeftSelectedIndex = -1;
        this.mLeftSelectedPart = null;
        this.mRightSelectedIndex = -1;
        this.mRightSelectedPart = null;
        this.mLeftPartIndex = -1;
        this.mRightPartIndex = -1;
    }

    public PairDataAdapter[] initializePairDataAdapters(PairClickListener pairClickListener, PairUnit currentUnit) {
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

        return new PairDataAdapter[] { new PairDataAdapter(leftPairParts, pairClickListener), new PairDataAdapter(rightPairParts, pairClickListener) };
    }

    public void switchPartState(String pairPart, PairDataAdapter leftDataAdapter, PairDataAdapter rightDataAdapter, Map<String, Integer> answerMap) {
        this.mLeftDataAdapter = leftDataAdapter;
        this.mRightDataAdapter = rightDataAdapter;
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
        final PairDataContainer[] leftPairDataContainers = this.mLeftDataAdapter.getDataContainers();
        final PairDataContainer[] rightPairDataContainers = this.mRightDataAdapter.getDataContainers();

        PairPartState currentState = null;
        for (int i = 0; i < leftPairDataContainers.length; i++) {
            final PairDataContainer leftContainer = leftPairDataContainers[i];
            final PairDataContainer rightContainer = rightPairDataContainers[i];
            final PairPartState leftPairState = leftContainer.getPairPartState();
            final PairPartState rightPairState = rightContainer.getPairPartState();

            if (leftPairState == PairPartState.Selected) {
                mLeftSelectedIndex = i;
                mLeftSelectedPart = leftContainer.getPairPart();
            } else if (leftPairState == PairPartState.Incorrect) {
                leftContainer.setPairPartState(PairPartState.Unselected);
            }
            if (rightPairState == PairPartState.Selected) {
                mRightSelectedIndex = i;
                mRightSelectedPart = rightContainer.getPairPart();
            } else if (rightPairState == PairPartState.Incorrect) {
                rightContainer.setPairPartState(PairPartState.Unselected);
            }
            if (leftContainer.getPairPart().equals(pairPart)) {
                currentState = leftPairState;
                mLeftPartIndex = i;
            } else if (rightContainer.getPairPart().equals(pairPart)) {
                currentState = rightPairState;
                mRightPartIndex = i;
            }
        }
        return currentState;
    }

    private void selectPart(String pairPart) {
        final PairDataContainer[] leftPairDataContainers = this.mLeftDataAdapter.getDataContainers();
        final PairDataContainer[] rightPairDataContainers = this.mRightDataAdapter.getDataContainers();

        PairPartState newState;
        // Check if two pair parts are selected
        final int currentPartIndex = this.mAnswerMap.get(pairPart);
        if (mLeftPartIndex >= 0 && mRightSelectedIndex >= 0) {
            // Two things are selected, lets check if they are correct
            if (currentPartIndex == this.mAnswerMap.get(mRightSelectedPart)) {
                newState = PairPartState.Correct;
            } else {
                newState = PairPartState.Incorrect;
            }
            leftPairDataContainers[mLeftPartIndex].setPairPartState(newState);
            rightPairDataContainers[mRightSelectedIndex].setPairPartState(newState);
            enableSelectableFields(leftPairDataContainers, rightPairDataContainers);
        } else if (mRightPartIndex >= 0 && mLeftSelectedIndex >= 0) {
            if (currentPartIndex == this.mAnswerMap.get(mLeftSelectedPart)) {
                newState = PairPartState.Correct;
            } else {
                newState = PairPartState.Incorrect;
            }
            leftPairDataContainers[mLeftSelectedIndex].setPairPartState(newState);
            rightPairDataContainers[mRightPartIndex].setPairPartState(newState);
            enableSelectableFields(leftPairDataContainers, rightPairDataContainers);
        } else {
            // Only this part is selected now
            if (mLeftPartIndex >= 0) {
                leftPairDataContainers[mLeftPartIndex].setPairPartState(PairPartState.Selected);
                disableNonSelectedFields(leftPairDataContainers);
            } else {
                rightPairDataContainers[mRightPartIndex].setPairPartState(PairPartState.Selected);
                disableNonSelectedFields(rightPairDataContainers);
            }
        }
    }

    private void unselectCurrentPart() {
        final PairDataContainer[] leftPairDataContainers = this.mLeftDataAdapter.getDataContainers();
        final PairDataContainer[] rightPairDataContainers = this.mRightDataAdapter.getDataContainers();

        // Unselect the current part
        if (mLeftPartIndex >= 0) {
            leftPairDataContainers[mLeftPartIndex].setPairPartState(PairPartState.Unselected);
            enableSelectableFields(leftPairDataContainers);
        } else {
            rightPairDataContainers[mRightPartIndex].setPairPartState(PairPartState.Unselected);
            enableSelectableFields(rightPairDataContainers);
        }
    }

    public Map<String, Integer> getAnswerMap() {
        return this.mAnswerMap;
    }
}
