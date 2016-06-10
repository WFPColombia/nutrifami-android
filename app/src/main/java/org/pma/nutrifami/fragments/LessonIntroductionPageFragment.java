package org.pma.nutrifami.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;

/**
 * Created by Peter on 10.06.2016.
 */
public class LessonIntroductionPageFragment extends Fragment {
    private static String INTRODUCTION_TITLE = "INTRODUCTION_TITLE";
    private static String INTRODUCTION_TEXT = "INTRODUCTION_TEXT";

    protected static Bundle getArguments(String title, String text) {
        Bundle args = new Bundle();
        args.putString(INTRODUCTION_TITLE, title);
        args.putString(INTRODUCTION_TEXT, text);
        return args;
    }

    public static Fragment newInstance(String title, String text) {
        Bundle args = getArguments(title, text);
        LessonIntroductionPageFragment fragment = new LessonIntroductionPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_lesson_introduction, container, false
        );

        final Bundle args = getArguments();

        TextView titleTextView = (TextView) rootView.findViewById(R.id.introduction_title);
        titleTextView.setText(args.getString(INTRODUCTION_TITLE));

        TextView introductionTextView = (TextView) rootView.findViewById(R.id.introduction_text);
        introductionTextView.setText(args.getString(INTRODUCTION_TEXT));

        return rootView;
    }
}
