package org.pma.nutrifami.view.activity.game;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.view.adapter.QuizAnswersDataAdapter;
import org.pma.nutrifami.view.listener.AnswerClickListener;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends GameActivity implements AnswerClickListener {

    private QuizAnswersDataAdapter mQuizAnswersDataAdapter;
    private TextView mQuestionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century_gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        setContentView(R.layout.activity_quiz);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.text_quiz_answers);
        assert recyclerView != null;

        recyclerView.setHasFixedSize(true);

        this.mQuestionTextView = (TextView) findViewById(R.id.text_quiz_question);

        // Initialize adapter with first answers
        this.mQuizAnswersDataAdapter = new QuizAnswersDataAdapter(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)));
    }
}
