package org.pma.nutrifami.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.pma.nutrifami.R;

/**
 * Created by Peter on 10.06.2016.
 */
public class LessonIntroductionPageFragment extends Fragment {
    private static final String INTRODUCTION_TITLE = "INTRODUCTION_TITLE";
    private static final String INTRODUCTION_TEXT = "INTRODUCTION_TEXT";

    private static Bundle getArguments(String title, String text) {
        final Bundle args = new Bundle();
        args.putString(INTRODUCTION_TITLE, title);
        args.putString(INTRODUCTION_TEXT, text);
        return args;
    }

    public static Fragment newInstance(String title, String text) {
        final Bundle args = getArguments(title, text);
        final LessonIntroductionPageFragment fragment = new LessonIntroductionPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_lesson_introduction, container, false
        );

        final Bundle args = getArguments();

        final TextView titleTextView = (TextView) rootView.findViewById(R.id.introduction_title);
        titleTextView.setText(args.getString(INTRODUCTION_TITLE));

        final TextView introductionTextView = (TextView) rootView.findViewById(R.id.introduction_text);
        introductionTextView.setText(args.getString(INTRODUCTION_TEXT));

        final ImageView introductionImageView = (ImageView) rootView.findViewById(R.id.introduction_image);
        introductionImageView.setImageResource(R.mipmap.m1);

        return rootView;
    }
}
