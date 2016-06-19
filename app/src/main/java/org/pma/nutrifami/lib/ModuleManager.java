package org.pma.nutrifami.lib;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.data.FileReadTask;
import org.pma.nutrifami.data.FileWriteTask;
import org.pma.nutrifami.data.GsonContainer;
import org.pma.nutrifami.data.GsonUnitDeserializer;
import org.pma.nutrifami.data.ModulesDownloadTask;
import org.pma.nutrifami.data.OnFileReadListener;
import org.pma.nutrifami.data.OnModulesLoadedListener;
import org.pma.nutrifami.demo.DemoModuleManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.model.unit.UnitType;
import org.pma.nutrifami.view.activity.game.PairsActivity;
import org.pma.nutrifami.view.activity.game.PictureQuizActivity;
import org.pma.nutrifami.view.activity.game.QuizActivity;
import org.pma.nutrifami.view.activity.game.SwipeActivity;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class ModuleManager {
    private static ModuleManager mInstance;

    public static ModuleManager getInstance() {
        if (mInstance == null) {
//            mInstance = new ModuleManager();
            mInstance = new DemoModuleManager();
        }
        return mInstance;
    }

    private Module[] mModules;

    private Class<? extends AppCompatActivity> resolveGameActivity(UnitType unitType) {
        switch (unitType) {
            case Pairs:
                return PairsActivity.class;
            case PictureQuiz:
                return PictureQuizActivity.class;
            case Swipe:
                return SwipeActivity.class;
            case TextQuiz:
                return QuizActivity.class;
        }
        return null;
    }

    public void loadModules(final Context context, final OnModulesLoadedListener onModulesLoadedListener) {
        if (this.mModules != null) {
            onModulesLoadedListener.onModulesLoaded(this.mModules);
            return;
        }
        new FileReadTask(context, new OnFileReadListener() {
            @Override
            public void onFileRead(String contents) {
                if (Constants.LOCAL_CACHE && contents != null) {
                    serveModules(
                            GsonUnitDeserializer.createGson().fromJson(contents, Module[].class),
                            onModulesLoadedListener
                    );
                } else {
                    // File is empty, we have to download things first.
                    new ModulesDownloadTask().download(context, new Response.Listener<GsonContainer<Module[]>>() {
                        @Override
                        public void onResponse(GsonContainer<Module[]> response) {
                            serveModules(response.getData(), onModulesLoadedListener);
                            new FileWriteTask(context, null)
                                    .execute(Constants.MODULES_FILE_NAME, response.getJson());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ModuleManager", "Module download went wrong: " + error.toString());
                        }
                    });
                }
            }
        }).execute(Constants.MODULES_FILE_NAME);
    }

    private void serveModules(Module[] modules, OnModulesLoadedListener onModulesLoadedListener) {
        this.mModules = modules;
        onModulesLoadedListener.onModulesLoaded(this.mModules);
    }

    public Class<? extends AppCompatActivity> getGameActivity(String lessonId, int position) {
        for (final Module mModule : mModules) {
            Lesson[] lessons = mModule.getLessons();
            for (final Lesson lesson : lessons) {
                if (lesson.getId().equals(lessonId)) {
                    return resolveGameActivity(lesson.getUnits()[position][0].getGameType());
                }
            }
        }
        return null;
    }

    protected void setModules(Module[] modules) {
        this.mModules = modules;
    }

    public Module getModule(String moduleId) {
        for (final Module mModule : mModules) {
            if (mModule.getId().equals(moduleId)) {
                return mModule;
            }
        }
        return null;
    }

    public Lesson getLesson(String lessonId) {
        for (final Module mModule : mModules) {
            Lesson[] lessons = mModule.getLessons();
            for (final Lesson lesson : lessons) {
                if (lesson.getId().equals(lessonId)) {
                    return lesson;
                }
            }
        }
        return null;
    }
}
