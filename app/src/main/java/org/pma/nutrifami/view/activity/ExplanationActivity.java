package org.pma.nutrifami.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import org.pma.nutrifami.R;
import org.pma.nutrifami.view.adapter.ExplanationPagerAdapter;

import me.crosswall.lib.coverflow.CoverFlow;

/**
 * Created by Peter on 18.06.2016.
 */
public class ExplanationActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_module_carousel);
        setupWindow();

        PagerAdapter mPagerAdapter = new ExplanationPagerAdapter(getSupportFragmentManager());

        final ViewPager pager = (ViewPager) findViewById(R.id.lesson_page_pager);
        pager.setOffscreenPageLimit(2);
        pager.setAdapter(mPagerAdapter);

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
}
