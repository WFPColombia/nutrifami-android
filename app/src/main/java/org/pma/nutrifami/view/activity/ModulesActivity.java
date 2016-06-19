package org.pma.nutrifami.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.data.ModulesDownloadTask;
import org.pma.nutrifami.data.OnModulesLoadedListener;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.SessionManager;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.view.adapter.ModulesDataAdapter;
import org.pma.nutrifami.view.custom.CustomLayoutManager;
import org.pma.nutrifami.view.listener.ModuleClickListener;

public class ModulesActivity extends AppCompatActivity implements ModuleClickListener, OnModulesLoadedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        setTitle(getString(R.string.modules_title));

        ModuleManager.getInstance().loadModules(this, this);
        // TODO: Show progress
    }

    public void onModulesLoaded(Module[] modules) {
        // TODO: Hide progress
        initializeRecyclerView(modules, (RecyclerView) findViewById(R.id.modules_recycler_view), this, this);
    }

    public static void initializeRecyclerView(
            Module[] modules,
            RecyclerView recyclerView,
            ModuleClickListener moduleClickListener,
            Context context
    ) {
        recyclerView.setHasFixedSize(true);

        final ModulesDataAdapter dataAdapter = new ModulesDataAdapter(moduleClickListener, modules);
        final LinearLayoutManager layoutManager = new CustomLayoutManager(context);

        layoutManager.setReverseLayout(true);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_modules, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                final Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View sender, String moduleId) {
        final Intent intent = new Intent(this, LessonActivity.class);

        intent.putExtra(Constants.LESSON_OVERVIEW, true);
        intent.putExtra(Constants.MODULE_ID, moduleId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sender, getString(R.string.mode_transition_name));
            startActivity(intent, transitionActivityOptions.toBundle());
        } else {
            startActivity(intent);
        }
    }
}

