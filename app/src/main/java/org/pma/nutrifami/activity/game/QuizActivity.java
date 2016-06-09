package org.pma.nutrifami.activity.game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.adapter.QuizAnswersDataAdapter;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.UnitExplanationManager;
import org.pma.nutrifami.listener.AnswerClickListener;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Unit;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizActivity extends GameActivity implements AnswerClickListener {

    private QuizAnswersDataAdapter mQuizAnswersDataAdapter;
    private TextView mQuestionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.text_quiz_answers);
        assert recyclerView != null;

        recyclerView.setHasFixedSize(true);

        this.mQuestionTextView = (TextView) findViewById(R.id.text_quiz_question);

        // Initialize adapter with first answers
        this.mQuizAnswersDataAdapter = new QuizAnswersDataAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        updateViewWithCurrentUnit();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.mQuizAnswersDataAdapter);
    }

    private void updateViewWithCurrentUnit() {
        this.mQuizAnswersDataAdapter.setAnswers(this.getCurrentUnit().getAnswers());
        this.mQuestionTextView.setText(this.getCurrentUnit().getQuestion());
    }

    @Override
    public void onAnswerClicked(int position) {
        final boolean correctAnswer = this.getCurrentUnit().getCorrectAnswer() == position;
        final DialogInterface.OnDismissListener dismiss = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateViewWithCurrentUnit();
            }
        };

        answerSelected(correctAnswer, dismiss, null);

    }
}
