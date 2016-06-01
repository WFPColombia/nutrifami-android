package org.pma.nutrifami.activity;

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
import android.widget.TextView;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.ModuleManager;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class LessonPageFragment extends Fragment {
    private final static String LESSON_TITLE = "Lesson_Title";
    private final static String LESSON_IMAGE = "Lesson_Image";
    private final static String LESSON_DESCRIPTION = "Lesson_Desc";

    public static LessonPageFragment newInstance(String id, String title, String image, String description) {
        Bundle args = new Bundle();
        args.putString(Constants.LESSON_ID, id);
        args.putString(LESSON_TITLE, title);
        args.putString(LESSON_IMAGE, image);
        args.putString(LESSON_DESCRIPTION, description);

        LessonPageFragment fragment = new LessonPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_lesson_page, container, false
        );

        final Bundle args = getArguments();
        final Context context = getContext();

        TextView titleTextView = (TextView) rootView.findViewById(R.id.lesson_title);
        titleTextView.setText(args.getString(LESSON_TITLE));

        // TODO: Set Image

        TextView descriptionTextView = (TextView) rootView.findViewById(R.id.lesson_description);
        descriptionTextView.setText(args.getString(LESSON_DESCRIPTION));

        Button playButton = (Button) rootView.findViewById(R.id.lesson_play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class<? extends AppCompatActivity> gameActivityClass = ModuleManager.getInstance().getGameActivity(args.getString(Constants.LESSON_ID));
                Intent intent = new Intent(context, gameActivityClass);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
