package org.pma.nutrifami.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.view.adapter.LessonPagePagerAdapter;
import org.pma.nutrifami.view.adapter.LessonUnitPagerAdapter;

import me.crosswall.lib.coverflow.CoverFlow;

public class LessonActivity extends FragmentActivity {
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_carousel);
        setupWindow();

        final ModuleManager moduleManager = ModuleManager.getInstance();
        final Intent intent = getIntent();
        final boolean isLessonOverview = intent.getBooleanExtra(Constants.LESSON_OVERVIEW, true);
        if (isLessonOverview) {
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

    private void setupWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final int accentColor = ContextCompat.getColor(this, R.color.colorAccent);
            final Window window = getWindow();
            window.setStatusBarColor(accentColor);
            window.setNavigationBarColor(accentColor);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.notifyDataSetChanged();
        }
    }
}
