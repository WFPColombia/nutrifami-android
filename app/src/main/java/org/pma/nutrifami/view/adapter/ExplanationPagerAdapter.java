package org.pma.nutrifami.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by Peter on 18.06.2016.
 */
public class ExplanationPagerAdapter extends FragmentStatePagerAdapter {
    public ExplanationPagerAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }
}
