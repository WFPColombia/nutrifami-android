package org.pma.nutrifami.view.activity.game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wenchao.cardstack.CardStack;

import org.pma.nutrifami.R;
import org.pma.nutrifami.view.adapter.SwipeCardDataAdapter;
import org.pma.nutrifami.model.SwipeUnit;

public class SwipeActivity extends GameActivity implements CardStack.CardEventListener {
    private CardStack mCardStack;
    private SwipeCardDataAdapter mSwipeCardDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        answerSelected(correct, null);
    }

    @Override
    public void topCardTapped() {

    }
}
