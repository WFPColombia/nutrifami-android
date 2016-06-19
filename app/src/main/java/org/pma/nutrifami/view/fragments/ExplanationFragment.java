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
    private TextView mTitleTextView;
    private TextView mFirstExplanationText;
    private TextView mSecondExplanationText;
    private TextView mThirdExplanationText;

    private ImageView mFirstExplanationIcon;
    private ImageView mSecondExplanationIcon;
    private ImageView mThirdExplanationIcon;


    public static Fragment createInstance() {
        return new ExplanationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView =  (ViewGroup) inflater.inflate(
                R.layout.explanation_fragment, container, false
        );

        this.mFirstExplanationIcon = (ImageView) rootView.findViewById(R.id.first_explanation_icon);
        this.mSecondExplanationIcon = (ImageView) rootView.findViewById(R.id.second_explanation_icon);
        this.mThirdExplanationIcon = (ImageView) rootView.findViewById(R.id.third_explanation_icon);

        this.mTitleTextView = (TextView) rootView.findViewById(R.id.explanation_title);
        this.mFirstExplanationText = (TextView) rootView.findViewById(R.id.first_explanation_text);
        this.mSecondExplanationText = (TextView) rootView.findViewById(R.id.second_explanation_text);
        this.mThirdExplanationText = (TextView) rootView.findViewById(R.id.third_explanation_text);

        return rootView;
    }

    public void bindToExplanation(BenefitsExplanation benefits) {

    }
}
