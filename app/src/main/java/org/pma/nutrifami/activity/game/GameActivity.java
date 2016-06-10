package org.pma.nutrifami.activity.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.UnitExplanationManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Unit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Peter on 09.06.2016.
 */
public abstract class GameActivity extends AppCompatActivity {
    private Lesson mLesson;
    private ArrayList<Unit> mUnits;
    private int mCurrentUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
        final String lessonId = intent.getStringExtra(Constants.LESSON_ID);
        final int unitsPosition = intent.getIntExtra(Constants.UNITS_POSITION, 0);

        this.mLesson = ModuleManager.getInstance().getLesson(lessonId);
        this.mUnits = new ArrayList<>(Arrays.asList(this.mLesson.getUnits()[unitsPosition]));
        this.mCurrentUnit = 0;
        setTitle(getLesson().getTitle());
    }

    protected Unit getCurrentUnit() {
        return this.mUnits.get(this.mCurrentUnit);
    }

    protected Lesson getLesson() {
        return this.mLesson;
    }

    protected ArrayList<Unit> getUnits() {
        return this.mUnits;
    }

    protected void answerSelected(boolean correctAnswer, DialogInterface.OnDismissListener dismiss, DialogInterface.OnDismissListener dismissLast) {
        if (dismissLast == null) {
            dismissLast = new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            };
        }

        String feedbackText;
        if (correctAnswer) {
            // Answer was correct
            feedbackText = getString(R.string.explanation_correct);
        } else {
            // Answer was not correct
            feedbackText = getString(R.string.explanation_false);
            this.mUnits.add(this.mUnits.get(this.mCurrentUnit));
        }

        DialogInterface.OnDismissListener dismissListener = null;
        if (this.mCurrentUnit == this.mUnits.size() - 1) {
            // This was the last unit -> game is over
            dismissListener = dismissLast;
        } else {
            this.mCurrentUnit++;
            dismissListener = dismiss;
        }

        UnitExplanationManager.getInstance().showExplanation(
            this,
            feedbackText,
            this.mUnits.get(this.mCurrentUnit).getAnswerExplanation(),
            dismissListener);
    }
}
