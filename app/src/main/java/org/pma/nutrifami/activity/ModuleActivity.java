package org.pma.nutrifami.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.adapter.LessonPagePagerAdapter;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.model.Module;

import me.crosswall.lib.coverflow.CoverFlow;

public class ModuleActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_carousel);

        ModuleManager moduleManager = ModuleManager.getInstance();
        Intent intent = getIntent();
        String moduleId = intent.getStringExtra(Constants.MODULE_ID);
        Module module = moduleManager.getModule(moduleId);

        ViewPager mPager = (ViewPager) findViewById(R.id.lesson_page_pager);
        mPager.setOffscreenPageLimit(2);

        PagerAdapter mPagerAdapter = new LessonPagePagerAdapter(getSupportFragmentManager(), module);
        mPager.setAdapter(mPagerAdapter);

        new CoverFlow.Builder()
                .with(mPager)
                .scale(0.15f)
                .build();
    }
}
