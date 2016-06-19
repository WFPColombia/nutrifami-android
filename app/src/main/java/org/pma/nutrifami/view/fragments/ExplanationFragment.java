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
import org.pma.nutrifami.model.BenefitsExplanation;

/**
 * Created by Peter on 18.06.2016.
 */
public class ExplanationFragment extends Fragment {
    public static final String EXPLANATION_TITLE = "EXPLANATION_TITLE";
    private static final String EXPLANATION_TEXT = "EXPLANATION_TEXT";
    private static final String EXPLANATION_ICON = "EXPLANATION_ICON";

    public static Fragment createInstance(BenefitsExplanation benefits) {
        final ExplanationFragment fragment = new ExplanationFragment();
        final Bundle args = new Bundle();
        args.putString(EXPLANATION_TITLE, benefits.getTitle());
        args.putString(EXPLANATION_TEXT + 1, benefits.getFirstExplanation());
        args.putString(EXPLANATION_TEXT + 2, benefits.getSecondExplanation());
        args.putString(EXPLANATION_TEXT + 3, benefits.getThirdExplanation());
        args.putInt(EXPLANATION_ICON + 1, benefits.getFirstExplanationIcon());
        args.putInt(EXPLANATION_ICON + 2, benefits.getSecondExplanationIcon());
        args.putInt(EXPLANATION_ICON + 3, benefits.getThirdExplanationIcon());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView =  (ViewGroup) inflater.inflate(
                R.layout.explanation_fragment, container, false
        );

        final ImageView firstExplanationIcon = (ImageView) rootView.findViewById(R.id.first_explanation_icon);
        final ImageView secondExplanationIcon = (ImageView) rootView.findViewById(R.id.second_explanation_icon);
        final ImageView thirdExplanationIcon = (ImageView) rootView.findViewById(R.id.third_explanation_icon);

        final TextView titleTextView = (TextView) rootView.findViewById(R.id.explanation_title);
        final TextView firstExplanationText = (TextView) rootView.findViewById(R.id.first_explanation_text);
        final TextView secondExplanationText = (TextView) rootView.findViewById(R.id.second_explanation_text);
        final TextView thirdExplanationText = (TextView) rootView.findViewById(R.id.third_explanation_text);

        final Bundle args = getArguments();

        titleTextView.setText(args.getString(EXPLANATION_TITLE));

        firstExplanationText.setText(args.getString(EXPLANATION_TEXT + 1));
        secondExplanationText.setText(args.getString(EXPLANATION_TEXT + 2));
        thirdExplanationText.setText(args.getString(EXPLANATION_TEXT + 3));

        firstExplanationIcon.setImageResource(args.getInt(EXPLANATION_ICON + 1));
        secondExplanationIcon.setImageResource(args.getInt(EXPLANATION_ICON + 2));
        thirdExplanationIcon.setImageResource(args.getInt(EXPLANATION_ICON + 3));

        return rootView;
    }
}
