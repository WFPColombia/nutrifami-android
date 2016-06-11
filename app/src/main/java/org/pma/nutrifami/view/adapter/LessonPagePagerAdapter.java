package org.pma.nutrifami.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.view.fragments.LessonOverviewPageFragment;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class LessonPagePagerAdapter extends LessonPageAdapter {
    private final Module mModule;

    public LessonPagePagerAdapter(FragmentManager fragmentManager, Module module) {
        super(fragmentManager);
        mModule = module;
    }

    @Override
    public Fragment getItem(int position) {
        final Lesson lesson = getModule().getLessons()[position];
        return LessonOverviewPageFragment.newInstance(lesson.getId(), lesson.getTitle(), lesson.getImage(), lesson.getDescription());
    }

    @Override
    public int getCount() {
        return getModule().getLessons().length;
    }

    private Module getModule() {
        return mModule;
    }
}
