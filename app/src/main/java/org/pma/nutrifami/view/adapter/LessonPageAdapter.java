package org.pma.nutrifami.view.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.pma.nutrifami.util.Updatable;

/**
 * Created by Peter Juras on 10.06.16.
 */

abstract class LessonPageAdapter extends FragmentStatePagerAdapter {
    LessonPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof Updatable) {
            ((Updatable) object).update();
        }
        return super.getItemPosition(object);
    }
}
