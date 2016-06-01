package org.pma.nutrifami.lib;

import android.support.v7.app.AppCompatActivity;

import org.pma.nutrifami.activity.game.QuizActivity;
import org.pma.nutrifami.mock.MockModuleManager;
import org.pma.nutrifami.model.Module;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class ModuleManager {
    private static ModuleManager mInstance;
    public static ModuleManager getInstance() {
        if (mInstance == null) {
            // TODO Change to real modules
            mInstance = new MockModuleManager();
//            mInstance = new ModuleManager();
        }
        return mInstance;
    }

    private Module[] mModules;

    public Module[] getModules() {
        return mModules;
    }

    public Class<? extends AppCompatActivity> getGameActivity(String lessonId) {
        // TODO: Retrieve GameType from lesson
        return QuizActivity.class;
    }

    public void setModules(Module[] modules) {
        this.mModules = modules;
    }
}
