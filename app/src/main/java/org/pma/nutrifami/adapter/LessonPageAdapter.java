package org.pma.nutrifami.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.pma.nutrifami.util.Updateable;

/**
 * Created by Peter Juras on 10.06.16.
 */

public abstract class LessonPageAdapter extends FragmentStatePagerAdapter {

    public LessonPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof Updateable) {
            ((Updateable) object).update();
        }
        return super.getItemPosition(object);
    }
}
