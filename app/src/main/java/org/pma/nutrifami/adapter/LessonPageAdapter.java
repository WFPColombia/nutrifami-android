package org.pma.nutrifami.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Peter Juras on 10.06.16.
 */

public abstract class LessonPageAdapter extends FragmentStatePagerAdapter {

    public LessonPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getItemPosition(Object object) {
        return FragmentStatePagerAdapter.POSITION_NONE;
    }
}
