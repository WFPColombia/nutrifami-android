package org.pma.nutrifami.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.adapter.LessonPagePagerAdapter;
import org.pma.nutrifami.adapter.LessonUnitPagerAdapter;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;

import me.crosswall.lib.coverflow.CoverFlow;

public class LectureActivity extends FragmentActivity implements DialogInterface.OnClickListener {
    private boolean mIsLessonOverview;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_carousel);

        final ModuleManager moduleManager = ModuleManager.getInstance();
        final Intent intent = getIntent();
        this.mIsLessonOverview = intent.getBooleanExtra(Constants.LESSON_OVERVIEW, true);
        if (mIsLessonOverview) {
            final String moduleId = intent.getStringExtra(Constants.MODULE_ID);
            final Module module = moduleManager.getModule(moduleId);
            this.mPagerAdapter = new LessonPagePagerAdapter(getSupportFragmentManager(), module);
        } else {
            final String lessonId = intent.getStringExtra(Constants.LESSON_ID);
            final Lesson lesson = moduleManager.getLesson(lessonId);
            this.mPagerAdapter = new LessonUnitPagerAdapter(getSupportFragmentManager(), lesson);
        }

        final ViewPager pager = (ViewPager) findViewById(R.id.lesson_page_pager);
        pager.setOffscreenPageLimit(2);
        pager.setAdapter(this.mPagerAdapter);

        new CoverFlow.Builder()
                .with(pager)
                .scale(0.15f)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
