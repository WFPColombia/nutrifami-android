package org.pma.nutrifami.lib;

import android.support.v7.app.AppCompatActivity;

import org.pma.nutrifami.activity.game.PairsActivity;
import org.pma.nutrifami.activity.game.PictureQuizActivity;
import org.pma.nutrifami.activity.game.QuizActivity;
import org.pma.nutrifami.activity.game.SwipeActivity;
import org.pma.nutrifami.mock.MockModuleManager;
import org.pma.nutrifami.model.UnitType;
import org.pma.nutrifami.model.Lesson;
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
        for (int i = 0; i < mModules.length; i++) {
            Lesson[] lessons = mModules[i].getLessons();
            for (int j = 0; j < lessons.length; j++) {
                Lesson lesson = lessons[j];
                if (lesson.getId().equals(lessonId)) {
                   return resolveGameActivity(lesson.getUnits()[position][0].getGameType());
                }
            }
        }
        return null;
    }

    public void setModules(Module[] modules) {
        this.mModules = modules;
    }

    public Module getModule(String moduleId) {
        for (int i = 0; i < mModules.length; i++) {
            if (mModules[i].getId().equals(moduleId)) {
                return mModules[i];
            }
        }
        return null;
    }

    public Lesson getLesson(String lessonId) {
        for (int i = 0; i < mModules.length; i++) {
            Lesson[] lessons = mModules[i].getLessons();
            for (int j = 0; j < lessons.length; j++) {
               if (lessons[j].getId().equals(lessonId)) {
                   return lessons[j];
               }
            }
        }
        return null;
    }
}
