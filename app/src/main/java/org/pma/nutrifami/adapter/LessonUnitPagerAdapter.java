package org.pma.nutrifami.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.pma.nutrifami.fragments.LessonGamePageFragment;
import org.pma.nutrifami.fragments.LessonIntroductionPageFragment;
import org.pma.nutrifami.fragments.LessonOverviewPageFragment;
import org.pma.nutrifami.model.IntroductionUnit;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Unit;
import org.pma.nutrifami.model.UnitType;

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
        Unit unit = this.mLesson.getUnits()[position][0];

        if (unit instanceof IntroductionUnit) {
            IntroductionUnit introductionUnit = (IntroductionUnit) unit;
            return LessonIntroductionPageFragment.newInstance(introductionUnit.getTitle(), introductionUnit.getDescription());
        } else {
            return LessonGamePageFragment.newInstance(mLesson.getId(), position, unit.getGameType().toString());
        }
    }

    @Override
    public int getCount() {
       return mLesson.getUnits().length;
    }
}
