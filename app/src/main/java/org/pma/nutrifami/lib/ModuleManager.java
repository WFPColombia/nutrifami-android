package org.pma.nutrifami.lib;

import android.support.v7.app.AppCompatActivity;

import org.pma.nutrifami.view.activity.game.PairsActivity;
import org.pma.nutrifami.view.activity.game.PictureQuizActivity;
import org.pma.nutrifami.view.activity.game.QuizActivity;
import org.pma.nutrifami.view.activity.game.SwipeActivity;
import org.pma.nutrifami.mock.MockModuleManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.model.UnitType;

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
        return this.mModules;
    }

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
