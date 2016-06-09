package org.pma.nutrifami.activity.game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.wenchao.cardstack.CardStack;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.adapter.SwipeCardDataAdapter;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.UnitExplanationManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Unit;

import java.util.ArrayList;
import java.util.Arrays;

public class SwipeActivity extends GameActivity implements CardStack.CardEventListener {

    private CardStack mCardStack;
    private SwipeCardDataAdapter mSwipeCardDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        mCardStack = (CardStack) findViewById(R.id.swipe_card_stack);

        assert mCardStack != null;
        mCardStack.setContentResource(R.layout.swipe_card);
        mCardStack.setStackMargin(20);
        mCardStack.setListener(this);

        // Get lesson data
        setTitle(getLesson().getTitle());

        mSwipeCardDataAdapter = new SwipeCardDataAdapter(getApplicationContext(), 0);
        // Add questions to cardDataAdapter

        final int length = getUnits().size();
        for (int i = 0; i < length; i++) {
            mSwipeCardDataAdapter.add(getUnits().get(i));
        }
        mCardStack.setAdapter(mSwipeCardDataAdapter);

        // Initialize 'no' & 'yes' buttons
        final Button noButton = (Button) findViewById(R.id.swipe_no_button);
        final Button yesButton = (Button) findViewById(R.id.swipe_yes_button);
        assert noButton != null;
        assert yesButton != null;

        final String[] answers = getCurrentUnit().getAnswers();

        noButton.setText(answers[0]);
        yesButton.setText(answers[1]);

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.discardTop(2);
            }
        });
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.discardTop(3);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean swipeEnd(int section, float distance) {
        Log.d("SwipeActivity", "Swipe section: " + section);
        return distance > 300;
    }

    @Override
    public boolean swipeStart(int section, float distance) {
        return true;
    }

    @Override
    public boolean swipeContinue(int section, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void discarded(int mIndex, int direction) {
        final int correctAnswer = getCurrentUnit().getCorrectAnswer();
        final boolean correct =
            (direction % 2 == 0 && correctAnswer == 0) ||
            (direction % 2 == 1 && correctAnswer == 1);
        if (!correct) {
            mSwipeCardDataAdapter.add(getCurrentUnit());
        }

        answerSelected(correct, null, null);
    }

    @Override
    public void topCardTapped() {

    }
}
