package org.pma.nutrifami.activity.game;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wenchao.cardstack.CardStack;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.adapter.SwipeCardData;
import org.pma.nutrifami.adapter.SwipeCardDataAdapter;
import org.pma.nutrifami.adapter.SwipeCardType;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.SwipeCardManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Unit;

import java.util.ArrayList;
import java.util.Arrays;

public class SwipeActivity extends AppCompatActivity implements CardStack.CardEventListener {

    private CardStack mCardStack;
    private SwipeCardDataAdapter mSwipeCardDataAdapter;
    private ArrayList<Unit> mUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        mCardStack = (CardStack) findViewById(R.id.swipe_card_stack);

        mCardStack.setContentResource(R.layout.swipe_card);
        mCardStack.setStackMargin(20);
        mCardStack.setListener(this);

        // Get lesson data
        final String lessonId = getIntent().getStringExtra(Constants.LESSON_ID);
        final Lesson lesson = ModuleManager.getInstance().getLesson(lessonId);
        mUnits = new ArrayList<Unit>();
        mUnits.addAll(Arrays.asList(lesson.getUnits()));

        setTitle(lesson.getTitle());

        mSwipeCardDataAdapter = new SwipeCardDataAdapter(getApplicationContext(), 0);
        // Add questions to cardDataAdapter

        final int length = mUnits.size();
        for (int i = 0; i < length; i++) {
            addCardForUnit(mUnits.get(i));
        }
        mCardStack.setAdapter(mSwipeCardDataAdapter);

        // Initialize 'no' & 'yes' buttons
        Button noButton = (Button) findViewById(R.id.swipe_no_button);
        Button yesButton = (Button) findViewById(R.id.swipe_yes_button);

        String[] answers = mUnits.get(0).getAnswers();
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

    private void addCardForUnit(Unit unit) {
        mSwipeCardDataAdapter.add(new SwipeCardData(unit, SwipeCardType.Question));
        mSwipeCardDataAdapter.add(new SwipeCardData(unit, SwipeCardType.Explanation));
    }

    @Override
    protected void onDestroy() {
        SwipeCardManager.getInstance().disposeContainers();
        super.onDestroy();
    }

    @Override
    public boolean swipeEnd(int section, float distance) {
        Log.d("Swipe direction", "" + section);
        if (distance <= 300) {
            return false;
        }

        // Don't do any logic if we are at an explanation card
        final int currentIndex = mCardStack.getCurrIndex();
        if (currentIndex % 2 != 0 || currentIndex >= mUnits.size() * 2) {
            return true;
        }

        Unit currentUnit = mUnits.get(currentIndex / 2);
        int correctAnswer = currentUnit.getCorrectAnswer();
        String feedbackText;
        if ((section % 2 == 0 && correctAnswer == 0) ||
                (section % 2 == 1 && correctAnswer == 1)) {
            // Answer is correct

            feedbackText = "Correcto!";
        } else {
           // Answer is not correct
            Log.d("Swipe", "Re-adding unit" + currentUnit);
            feedbackText = "Not correcto!";
            addCardForUnit(currentUnit);
            mUnits.add(currentUnit);
        }

        Toast toast = Toast.makeText(getApplicationContext(), feedbackText, Toast.LENGTH_LONG);
        toast.show();

        return true;
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
        Log.d("Discarded", "Direction: "  + direction);
        SwipeCardManager.getInstance().updateContainer(mIndex);
        Log.d("Discarded mIndex", "" + mIndex);
//        mCardStack.getCurrIndex();
        Log.d("CardStack current index", "" + mCardStack.getCurrIndex());
    }

    @Override
    public void topCardTapped() {
    }
}
