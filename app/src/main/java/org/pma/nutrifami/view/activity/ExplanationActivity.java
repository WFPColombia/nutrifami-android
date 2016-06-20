package org.pma.nutrifami.view.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.badoualy.stepperindicator.StepperIndicator;

import org.pma.nutrifami.R;
import org.pma.nutrifami.view.adapter.ExplanationPagerAdapter;
import org.pma.nutrifami.view.fragments.ModulesExplanationFragment;

import me.crosswall.lib.coverflow.CoverFlow;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Peter on 18.06.2016.
 */
public class ExplanationActivity extends AppCompatActivity {
    public static ExplanationActivity currentInstance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentInstance = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century_gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_explanation);
        setupWindow();

        PagerAdapter mPagerAdapter = new ExplanationPagerAdapter(getSupportFragmentManager());

        final ViewPager pager = (ViewPager) findViewById(R.id.lesson_page_pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(mPagerAdapter);

        final ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ModulesExplanationFragment.scrollModules();
                        }
                    }, 250);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        pager.addOnPageChangeListener(listener);

        new CoverFlow.Builder()
                .with(pager)
                .scale(0.15f)
                .build();

        final StepperIndicator indicator = (StepperIndicator) findViewById(R.id.stepper_indicator);
        indicator.setViewPager(pager);
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)));
    }
}
