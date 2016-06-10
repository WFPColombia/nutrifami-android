package org.pma.nutrifami.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;

/**
 * Created by Peter on 10.06.2016.
 */
public class LessonIntroductionPageFragment extends Fragment {
    protected final static String LESSON_TITLE = "Lesson_Title";
    protected final static String LESSON_IMAGE = "Lesson_Image";
    protected final static String LESSON_DESCRIPTION = "Lesson_Desc";

    protected static Bundle getArguments(String id, String title, String image, String description) {
        Bundle args = new Bundle();
        args.putString(Constants.LESSON_ID, id);
        args.putString(LESSON_TITLE, title);
        args.putString(LESSON_IMAGE, image);
        args.putString(LESSON_DESCRIPTION, description);
        return args;
    }

    public static Fragment newInstance(String id, String title, String image, String description) {
        Bundle args = getArguments(id, title, image, description);
        LessonIntroductionPageFragment fragment = new LessonIntroductionPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @LayoutRes
    private int mLayoutResource;

    public LessonIntroductionPageFragment() {
        this.mLayoutResource = R.layout.fragment_lesson_introduction;
    }

    protected void setLayoutResource(@LayoutRes int layoutResource) {
        this.mLayoutResource = layoutResource;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                this.mLayoutResource, container, false
        );

        final Bundle args = getArguments();

        TextView titleTextView = (TextView) rootView.findViewById(R.id.lesson_title);
        titleTextView.setText(args.getString(LESSON_TITLE));

        TextView descriptionTextView = (TextView) rootView.findViewById(R.id.lesson_description);
        descriptionTextView.setText(args.getString(LESSON_DESCRIPTION));

        return rootView;
    }
}
