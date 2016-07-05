package org.pma.nutrifami.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.GameTypeManager;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.SessionManager;
import org.pma.nutrifami.model.Lesson;

/**
 * Created by Peter Juras on 10.06.16.
 */

public class LessonGamePageFragment extends LessonProgressFragment {
    private final static String GAME_TYPE = "GAME_TYPE";

    private static Bundle getArguments(String lessonId, int unitsPosition, String gameType) {
        final Bundle args = new Bundle();
        args.putString(Constants.LESSON_ID, lessonId);
        args.putInt(Constants.UNITS_POSITION, unitsPosition);
        args.putString(GAME_TYPE, gameType);
        return args;
    }

    public static Fragment newInstance(String id, int unitsPosition, String gameType) {
        final Bundle args = getArguments(id, unitsPosition, gameType);
        final LessonGamePageFragment fragment = new LessonGamePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_lesson_game, container, false
        );

        final Bundle args = getArguments();
        final Context context = rootView.getContext();

        final GameTypeManager manager = GameTypeManager.getInstance();
        final String gameType = args.getString(GAME_TYPE);
        final String lessonId = args.getString(Constants.LESSON_ID);
        final int unitsPosition = args.getInt(Constants.UNITS_POSITION);
        final Lesson lesson = ModuleManager.getInstance().getLesson(lessonId);

        final TextView titleTextView = (TextView) rootView.findViewById(R.id.game_title);
        titleTextView.setText(manager.getGameTitle(context, gameType));

        final TextView descriptionTextView = (TextView) rootView.findViewById(R.id.game_description);
        descriptionTextView.setText(manager.getGameDescription(context, gameType));

        final ImageView gameImageView = (ImageView) rootView.findViewById(R.id.game_image);
        final int gameImage = manager.getGameImage(gameType);
        gameImageView.setImageResource(gameImage);

        if (SessionManager.getInstance().areUnitPackagesCompleted(context, lesson, unitsPosition)) {
            final ImageView completedImageView = (ImageView) rootView.findViewById(R.id.card_completed_image_view);
            completedImageView.setVisibility(View.VISIBLE);
        }

        final Button playButton = (Button) rootView.findViewById(R.id.game_play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class<? extends AppCompatActivity> gameClass = ModuleManager.getInstance().getGameActivity(lessonId, unitsPosition);
                Intent intent = new Intent(context, gameClass);
                intent.putExtra(Constants.LESSON_ID, lessonId);
                intent.putExtra(Constants.UNITS_POSITION, unitsPosition);
                startActivity(intent);
            }
        });

        final Animation fadeAnimation = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.fade);
        fadeAnimation.setStartOffset(250);
        fadeAnimation.setDuration(250);
        rootView.startAnimation(fadeAnimation);
        return rootView;
    }

    @Override
    public void update() {
        final Bundle args = getArguments();
        final Lesson lesson = ModuleManager.getInstance().getLesson(args.getString(Constants.LESSON_ID));
        final int unitsPosition = args.getInt(Constants.UNITS_POSITION);

        if (SessionManager.getInstance().areUnitPackagesCompleted(getContext(), lesson, unitsPosition)) {
            super.update();
        }
    }
}
