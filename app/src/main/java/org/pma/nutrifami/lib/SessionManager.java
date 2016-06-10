package org.pma.nutrifami.lib;

import android.content.Context;
import android.content.SharedPreferences;

import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Peter Juras on 10.06.16.
 */

public class SessionManager {
    private static SessionManager mInstance;
    private static String SHARED_PREFERENCES_NAME = "Nutrifami.config";
    private static String FIRST_LAUNCH = "FIRST_LAUNCH";
    private static String COMPLETED_LESSONS = "COMPLETED_LESSONS";

    public static SessionManager getInstance() {
        if (mInstance == null) {
            mInstance = new SessionManager();
        }
        return mInstance;
    }

    private SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    private String formatUnitPackage(String lessonId, int unitPackage) {
        return lessonId + ":::" + unitPackage;
    }

    public boolean isFirstLaunch(Context context) {
        return getPreferences(context).getBoolean(FIRST_LAUNCH, true);
    }

    public void setFirstLaunch(Context context, boolean firstLaunch) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(FIRST_LAUNCH, firstLaunch);
        editor.apply();
    }

    public Set<String> getCompletedLessons(Context context) {
        return getPreferences(context).getStringSet(COMPLETED_LESSONS, new HashSet<String>());
    }

    public void setUnitPackageAsCompleted(Context context, Lesson lesson, int unitPackage) {
        Set<String> completedLessons = getCompletedLessons(context);
        completedLessons.add(formatUnitPackage(lesson.getId(), unitPackage));

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putStringSet(COMPLETED_LESSONS, completedLessons);
        editor.apply();
    }

    public boolean areUnitPackagesCompleted(Context context, Lesson lesson, int... unitPackages) {
        Set<String> completedLessons = getCompletedLessons(context);
        Set<String> formattedNames = new HashSet<>();
        for (int unitPackage : unitPackages) {
            formattedNames.add(formatUnitPackage(lesson.getId(), unitPackage));
        }
        return completedLessons.containsAll(formattedNames);
    }

    public boolean areLessonsCompleted(Context context, Lesson... lessons) {
        for (Lesson lesson : lessons) {
            if (!areUnitPackagesCompleted(context, lesson, range(1, lesson.getUnits().length))) {
                return false;
            }
        }
        return true;
    }

    public boolean isModuleCompleted(Context context, Module module) {
        return areLessonsCompleted(context, module.getLessons());
    }

    public static int[] range(int min, int max) {
        int[] range = new int[max - min];
        int currentNumber = min;
        for (int i = 0; i < range.length; i++) {
            range[i] = currentNumber++;
        }
        return range;
    }
}
