package org.pma.nutrifami.view.activity.game;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.SessionManager;
import org.pma.nutrifami.lib.UnitExplanationManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.unit.SwipeUnit;
import org.pma.nutrifami.model.unit.Unit;
import org.pma.nutrifami.view.activity.FeedbackActivity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Peter on 09.06.2016.
 */
public abstract class GameActivity extends AppCompatActivity {
    private Lesson mLesson;
    private ArrayList<Unit> mUnits;
    private int mCurrentUnit;
    private int mUnitsPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
        final String lessonId = intent.getStringExtra(Constants.LESSON_ID);

        this.mUnitsPosition = intent.getIntExtra(Constants.UNITS_POSITION, 0);
        this.mLesson = ModuleManager.getInstance().getLesson(lessonId);
        this.mUnits = new ArrayList<>(Arrays.asList(this.mLesson.getUnits()[this.mUnitsPosition]));
        this.mCurrentUnit = 0;
        setTitle(getLesson().getTitle());
    }

    Unit getCurrentUnit() {
        return this.mUnits.get(this.mCurrentUnit);
    }

    protected int getUnitsPosition() {
        return this.mUnitsPosition;
    }
    protected Lesson getLesson() {
        return this.mLesson;
    }

    ArrayList<Unit> getUnits() {
        return this.mUnits;
    }

    protected void saveAndFinish(Context context, Lesson lesson) {
        SessionManager.getInstance().setUnitPackageAsCompleted(context, lesson, mUnitsPosition);
        final Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
        finish();
    }

    void answerSelected(boolean correctAnswer, DialogInterface.OnDismissListener dismiss, DialogInterface.OnDismissListener dismissLast) {
        final Context context = this;
        final Lesson lesson = this.mLesson;
        if (dismissLast == null) {
            dismissLast = new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    saveAndFinish(context, lesson);
                }
            };
        }

        final String answerExplanation = this.mUnits.get(this.mCurrentUnit).getAnswerExplanation();
        String feedbackText;
        if (correctAnswer) {
            // Answer was correct
            feedbackText = getString(R.string.explanation_correct);
        } else {
            // Answer was not correct
            feedbackText = getString(R.string.explanation_false);
            this.mUnits.add(this.mUnits.get(this.mCurrentUnit));
        }

        DialogInterface.OnDismissListener dismissListener;
        if (isLastUnit()) {
            // This was the last unit -> game is over
            dismissListener = dismissLast;
        } else {
            increaseUnitCounter();
            dismissListener = dismiss;
        }

        UnitExplanationManager.getInstance().showExplanation(
                this,
                feedbackText,
                answerExplanation,
                dismissListener);
    }

    protected boolean isLastUnit() {
        return this.mCurrentUnit == this.mUnits.size() - 1;
    }

    protected void increaseUnitCounter() {
        this.mCurrentUnit++;
    }
}
