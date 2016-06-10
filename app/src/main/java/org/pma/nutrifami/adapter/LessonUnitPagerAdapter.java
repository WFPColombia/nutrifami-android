package org.pma.nutrifami.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.pma.nutrifami.fragments.LessonIntroductionPageFragment;
import org.pma.nutrifami.fragments.LessonPageFragment;
import org.pma.nutrifami.model.UnitType;
import org.pma.nutrifami.model.Lesson;

/**
 * Created by Peter on 10.06.2016.
 */
public class LessonUnitPagerAdapter extends FragmentStatePagerAdapter {
    private Lesson mLesson;

    public LessonUnitPagerAdapter(FragmentManager fragmentManager, Lesson lesson) {
        super(fragmentManager);
        this.mLesson = lesson;
    }

    @Override
    public Fragment getItem(int position) {
        if (this.mLesson.getUnits()[position].getGameType() == UnitType.Introduction) {
            return new LessonIntroductionPageFragment();
        } else {
            return new LessonPageFragment();
        }
    }

    @Override
    public int getCount() {
       return mLesson.getUnits().length;
    }
}
