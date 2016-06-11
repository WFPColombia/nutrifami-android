package org.pma.nutrifami.lib;

import android.content.Context;
import android.content.SharedPreferences;

import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.util.Range;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Peter Juras on 10.06.16.
 */

public class SessionManager {
    private static SessionManager mInstance;
    private static final String SHARED_PREFERENCES_NAME = "Nutrifami.config";
    private static final String FIRST_LAUNCH = "FIRST_LAUNCH";
    private static final String COMPLETED_LESSONS = "COMPLETED_LESSONS";

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
        final SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(FIRST_LAUNCH, firstLaunch);
        editor.apply();
    }

    private Set<String> getCompletedLessons(Context context) {
        return getPreferences(context).getStringSet(COMPLETED_LESSONS, new HashSet<String>());
    }

    public void setUnitPackageAsCompleted(Context context, Lesson lesson, int unitPackage) {
        final Set<String> completedLessons = new HashSet<>(getCompletedLessons(context));
        completedLessons.add(formatUnitPackage(lesson.getId(), unitPackage));

        final SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putStringSet(COMPLETED_LESSONS, completedLessons);
        editor.apply();
    }

    public boolean areUnitPackagesCompleted(Context context, Lesson lesson, int... unitPackages) {
        final Set<String> completedLessons = getCompletedLessons(context);
        final Set<String> formattedNames = new HashSet<>();
        for (int unitPackage : unitPackages) {
            formattedNames.add(formatUnitPackage(lesson.getId(), unitPackage));
        }
        return completedLessons.containsAll(formattedNames);
    }

    public boolean areLessonsCompleted(Context context, Lesson... lessons) {
        for (final Lesson lesson : lessons) {
            if (!areUnitPackagesCompleted(context, lesson, Range.range(1, lesson.getUnits().length))) {
                return false;
            }
        }
        return true;
    }

    public boolean isModuleCompleted(Context context, Module module) {
        return areLessonsCompleted(context, module.getLessons());
    }
}
