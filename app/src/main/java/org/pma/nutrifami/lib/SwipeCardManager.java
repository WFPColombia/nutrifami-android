package org.pma.nutrifami.lib;

import org.pma.nutrifami.adapter.SwipeCardData;
import org.pma.nutrifami.model.Unit;

import java.util.ArrayList;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeCardManager {
    private static SwipeCardManager mInstance;
    public static SwipeCardManager getInstance() {
        if (mInstance == null) {
            mInstance = new SwipeCardManager();
        }
        return mInstance;
    }

    private ArrayList<SwipeCardStateContainer> mCardContainers;

    public SwipeCardManager() {
       mCardContainers = new ArrayList<SwipeCardStateContainer>();
    }

    public void disposeContainers() {
        mCardContainers.clear();
    }

    public void addContainer(SwipeCardStateContainer swipeCardStateContainer) {
       mCardContainers.add(swipeCardStateContainer);
    }

    public void updateContainer(int index) {
        if (mCardContainers.size() <= index) {
            return;
        }
        mCardContainers.get(index).updateView();
    }

}
