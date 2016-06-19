package org.pma.nutrifami.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import org.pma.nutrifami.model.BenefitsExplanation;
import org.pma.nutrifami.model.ScreenshotExplanation;
import org.pma.nutrifami.view.fragments.ExplanationFragment;
import org.pma.nutrifami.view.fragments.ModulesExplanationFragment;
import org.pma.nutrifami.view.fragments.ScreenshotExplanationFragment;

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
        switch (position) {
            case 0:
                return ExplanationFragment.createInstance(BenefitsExplanation.getBenefits());
            case 1:
                return ScreenshotExplanationFragment.createInstance(ScreenshotExplanation.getProfileExplanation());
            case 2:
                return ModulesExplanationFragment.createInstance(ScreenshotExplanation.getTrainingExplanation());
            default:
                return null;
        }
    }
}
