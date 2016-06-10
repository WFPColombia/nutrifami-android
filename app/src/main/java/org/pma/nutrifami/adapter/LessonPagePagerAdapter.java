package org.pma.nutrifami.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.pma.nutrifami.fragments.LessonPageFragment;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class LessonPagePagerAdapter extends FragmentStatePagerAdapter {
    private Module mModule;

    public LessonPagePagerAdapter(FragmentManager fragmentManager, Module module) {
        super(fragmentManager);
        mModule = module;
    }

    @Override
    public Fragment getItem(int position) {
        Lesson lesson = getModule().getLessons()[position];
        return LessonPageFragment.newInstance(lesson.getId(), lesson.getTitle(), lesson.getImage(), lesson.getDescription());
    }

    @Override
    public int getCount() {
        return getModule().getLessons().length;
    }

    public Module getModule() {
        return mModule;
    }
}
