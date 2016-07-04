package org.pma.nutrifami.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.view.adapter.LessonPagePagerAdapter;
import org.pma.nutrifami.view.adapter.LessonUnitPagerAdapter;

import me.crosswall.lib.coverflow.CoverFlow;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LessonActivity extends FragmentActivity {
    private PagerAdapter mPagerAdapter;
    private TextView mVoucherCreditsText;
    public static int voucherCredits = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century_gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        setContentView(R.layout.activity_module_carousel);
        setupWindow();

        this.mVoucherCreditsText = (TextView) findViewById(R.id.voucher_credits_text);
        final Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade);
        fade.setStartOffset(250);
        fade.setDuration(250);
        this.mVoucherCreditsText.startAnimation(fade);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                prepareView();
            }
        }, 250);
    }

    private void prepareView() {
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

    @Override
    protected void onResume() {
        super.onResume();
        if (this.mVoucherCreditsText != null) {
            this.mVoucherCreditsText.setText("Voucher Credit: " + voucherCredits + " COL$");
        }
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)));
    }
}
