package org.pma.nutrifami.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.activity.LectureActivity;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class LessonOverviewPageFragment extends Fragment {
    private final static String LESSON_TITLE = "Lesson_Title";
    private final static String LESSON_IMAGE = "Lesson_Image";
    private final static String LESSON_DESCRIPTION = "Lesson_Desc";

    private static Bundle getArguments(String id, String title, String image, String description) {
        Bundle args = new Bundle();
        args.putString(Constants.LESSON_ID, id);
        args.putString(LESSON_TITLE, title);
        args.putString(LESSON_IMAGE, image);
        args.putString(LESSON_DESCRIPTION, description);
        return args;
    }

    public static Fragment newInstance(String id, String title, String image, String description) {
        Bundle args = getArguments(id, title, image, description);
        LessonOverviewPageFragment fragment = new LessonOverviewPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(
                R.layout.fragment_lesson_page, container, false
        );

        final Bundle args = getArguments();
        final Context context = getContext();

        TextView titleTextView = (TextView) rootView.findViewById(R.id.lesson_title);
        titleTextView.setText(args.getString(LESSON_TITLE));

        TextView descriptionTextView = (TextView) rootView.findViewById(R.id.lesson_description);
        descriptionTextView.setText(args.getString(LESSON_DESCRIPTION));

        Button startButton = (Button) rootView.findViewById(R.id.lesson_play_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LectureActivity.class);
                intent.putExtra(Constants.LESSON_ID, args.getString(Constants.LESSON_ID));
                intent.putExtra(Constants.LESSON_OVERVIEW, false);
                startActivity(intent);
            }
        });

        return rootView;
    }
}