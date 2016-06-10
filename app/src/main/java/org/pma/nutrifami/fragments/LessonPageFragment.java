package org.pma.nutrifami.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.activity.LectureActivity;
import org.pma.nutrifami.lib.ModuleManager;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class LessonPageFragment extends LessonIntroductionPageFragment {
    private static final String IS_LESSON_OVERVIEW = "IS_LESSON_OVERVIEW";

    private static Bundle getArguments(String id, String title, String image, String description, boolean isLessonOverview) {
        Bundle args = LessonIntroductionPageFragment.getArguments(id, title, image, description);
        args.putBoolean(IS_LESSON_OVERVIEW, isLessonOverview);
        return args;
    }

    public static Fragment newInstance(String id, String title, String image, String description, boolean isLessonOverview) {
        Bundle args = getArguments(id, title, image, description, isLessonOverview);
        LessonPageFragment fragment = new LessonPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LessonPageFragment() {
        super();
        setLayoutResource(R.layout.fragment_lesson_page);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        final Bundle args = getArguments();
        final Context context = getContext();
        View.OnClickListener onClickListener;

        final boolean isLessonOverview = args.getBoolean(IS_LESSON_OVERVIEW);
        if (isLessonOverview) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class<? extends AppCompatActivity> gameActivityClass = ModuleManager.getInstance().getGameActivity(args.getString(Constants.LESSON_ID));
                    Intent intent = new Intent(context, gameActivityClass);
                    intent.putExtra(Constants.LESSON_ID, args.getString(Constants.LESSON_ID));
                    startActivity(intent);
                }
            };
        } else {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LectureActivity.class);
                    intent.putExtra(Constants.LESSON_OVERVIEW, false);
                    intent.putExtra(Constants.LESSON_ID, args.getString(Constants.LESSON_ID));
                    startActivity(intent);
                }
            };
        }

        Button playButton = (Button) rootView.findViewById(R.id.lesson_play_button);
        playButton.setOnClickListener(onClickListener);

        return rootView;
    }
}
