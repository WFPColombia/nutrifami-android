package org.pma.nutrifami.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.unit.IntroductionUnit;
import org.pma.nutrifami.model.unit.Unit;
import org.pma.nutrifami.view.fragments.LessonGamePageFragment;
import org.pma.nutrifami.view.fragments.LessonIntroductionPageFragment;

/**
 * Created by Peter on 10.06.2016.
 */
public class LessonUnitPagerAdapter extends LessonPageAdapter {
    private final Lesson mLesson;

    public LessonUnitPagerAdapter(FragmentManager fragmentManager, Lesson lesson) {
        super(fragmentManager);
        this.mLesson = lesson;
    }

    @Override
    public Fragment getItem(int position) {
        final Unit unit = this.mLesson.getUnits()[position][0];

        if (unit instanceof IntroductionUnit) {
            final IntroductionUnit introductionUnit = (IntroductionUnit) unit;
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
