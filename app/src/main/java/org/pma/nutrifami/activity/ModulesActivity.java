package org.pma.nutrifami.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.SessionManager;
import org.pma.nutrifami.model.Module;

public class ModulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        final Button firstModuleButton = (Button) findViewById(R.id.first_module_button);
        assert firstModuleButton != null;
        final ModulesActivity activity = this;

        setTitle(getString(R.string.modules_title));

        firstModuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Module module = ModuleManager.getInstance().getModules()[0];
                final Intent intent = new Intent(activity, LectureActivity.class);

                intent.putExtra(Constants.LESSON_OVERVIEW, true);
                intent.putExtra(Constants.MODULE_ID, module.getId());

                startActivity(intent);
            }
        });
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
}
