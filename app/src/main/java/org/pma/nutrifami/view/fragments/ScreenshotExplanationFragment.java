package org.pma.nutrifami.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.model.ScreenshotExplanation;

import static org.pma.nutrifami.view.fragments.ExplanationFragment.EXPLANATION_TITLE;

/**
 * Created by juras on 19-Jun-16.
 */

public class ScreenshotExplanationFragment extends Fragment {

    public static final String EXPLANATION_DESCRIPTION = "EXPLANATION_DESCRIPTION";
    private static final String EXPLANATION_SCREENSHOT = "EXPLANATION_SCREENSHOT";
    private static final String EXPLANATION_ACTIONABLE = "EXPLANATION_ACTIONABLE";

    public static ScreenshotExplanationFragment createInstance(ScreenshotExplanation explanation) {
        final Bundle args = new Bundle();
        args.putString(EXPLANATION_TITLE, explanation.getTitle());
        args.putString(EXPLANATION_DESCRIPTION, explanation.getDescription());
        args.putInt(EXPLANATION_SCREENSHOT, explanation.getScreenshot());
        args.putBoolean(EXPLANATION_ACTIONABLE, explanation.isActionable());

        final ScreenshotExplanationFragment fragment = new ScreenshotExplanationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView =  (ViewGroup) inflater.inflate(
                R.layout.screenshot_explanation_fragment, container, false
        );

        final Bundle args = getArguments();
        final TextView titleText = (TextView) rootView.findViewById(R.id.explanation_title);
        final TextView descriptionText = (TextView) rootView.findViewById(R.id.explanation_description);
        final ImageView screenshotImage = (ImageView) rootView.findViewById(R.id.explanation_screenshot);
        final Button playButton = (Button) rootView.findViewById(R.id.explanation_play_button);

        titleText.setText(args.getString(EXPLANATION_TITLE));
        descriptionText.setText(args.getString(EXPLANATION_DESCRIPTION));
        screenshotImage.setImageResource(args.getInt(EXPLANATION_SCREENSHOT));
        playButton.setVisibility(args.getBoolean(EXPLANATION_ACTIONABLE) ? View.VISIBLE : View.INVISIBLE);

        return rootView;
    }
}
