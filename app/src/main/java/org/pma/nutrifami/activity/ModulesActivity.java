package org.pma.nutrifami.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.adapter.ModulesDataAdapter;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.SessionManager;
import org.pma.nutrifami.listener.ModuleClickListener;
import org.pma.nutrifami.model.Module;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ModulesActivity extends AppCompatActivity implements ModuleClickListener {
    private ModulesDataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.modules_recycler_view);
        assert recyclerView != null;

        recyclerView.setHasFixedSize(true);

        this.mDataAdapter = new ModulesDataAdapter(this, ModuleManager.getInstance().getModules());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setReverseLayout(true);
        recyclerView.setAdapter(this.mDataAdapter);
        recyclerView.setLayoutManager(layoutManager);
        // TODO: Focus based on progress

        setTitle(getString(R.string.modules_title));
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
    protected void onResume() {
        super.onResume();

        if (SessionManager.getInstance().isFirstLaunch(this)) {
            final Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (this.mDataAdapter != null) {
            this.mDataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(String moduleId) {
        final Intent intent = new Intent(this, LectureActivity.class);

        intent.putExtra(Constants.LESSON_OVERVIEW, true);
        intent.putExtra(Constants.MODULE_ID, moduleId);

        startActivity(intent);
    }
}
