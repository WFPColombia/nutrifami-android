package org.pma.nutrifami.view.activity.game;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wenchao.cardstack.CardStack;

import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.SessionManager;
import org.pma.nutrifami.model.unit.SwipeUnit;
import org.pma.nutrifami.view.activity.FeedbackActivity;
import org.pma.nutrifami.view.adapter.SwipeCardDataAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SwipeActivity extends GameActivity implements CardStack.CardEventListener {
    private CardStack mCardStack;
    private SwipeCardDataAdapter mSwipeCardDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century_gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_swipe);

        this.mCardStack = (CardStack) findViewById(R.id.swipe_card_stack);
        assert mCardStack != null;

        mCardStack.setContentResource(R.layout.swipe_card);
        mCardStack.setStackMargin(20);
        mCardStack.setListener(this);

        mSwipeCardDataAdapter = new SwipeCardDataAdapter(getApplicationContext());
        // Add questions to cardDataAdapter

        mSwipeCardDataAdapter.addAll(getUnits());
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

        setTitle(getLesson().getTitle());
    }

    @Override
    public boolean swipeEnd(int section, float distance) {
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
        final boolean isTrue = ((SwipeUnit) getCurrentUnit()).isTrue();
        final boolean correct = (direction % 2 == 0 && !isTrue) || (direction % 2 == 1 && isTrue);
        if (!correct) {
            mSwipeCardDataAdapter.add(getCurrentUnit());
        }

        if (correct) {
            mTotalCorrect++;
        }
        setTitle(getLesson().getTitle() + ": " + mTotalCorrect + "/" + getLesson().getUnits()[1].length);
        answerSelected(correct, null, null);
    }

    private int mTotalCorrect = 0;

    @Override
    public void topCardTapped() {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)));
    }
}
