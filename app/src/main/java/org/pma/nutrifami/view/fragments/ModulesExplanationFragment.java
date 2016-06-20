package org.pma.nutrifami.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.R;
import org.pma.nutrifami.data.OnModulesLoadedListener;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.model.ScreenshotExplanation;
import org.pma.nutrifami.view.activity.ExplanationActivity;
import org.pma.nutrifami.view.activity.LessonActivity;
import org.pma.nutrifami.view.activity.ModulesActivity;
import org.pma.nutrifami.view.listener.ModuleClickListener;

import static org.pma.nutrifami.view.fragments.ExplanationFragment.EXPLANATION_TITLE;
import static org.pma.nutrifami.view.fragments.ScreenshotExplanationFragment.EXPLANATION_DESCRIPTION;

/**
 * Created by juras on 19-Jun-16.
 */

public class ModulesExplanationFragment extends Fragment {
    private static View sharedElement;

    public static ModulesExplanationFragment createInstance(ScreenshotExplanation explanation) {
        final Bundle args = new Bundle();
        args.putString(EXPLANATION_TITLE, explanation.getTitle());
        args.putString(EXPLANATION_DESCRIPTION, explanation.getDescription());
        final ModulesExplanationFragment fragment = new ModulesExplanationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static void scrollModules() {
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(5);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.smoothScrollToPosition(0);
                }
            }, 2000);
        }
    }
    private static RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.module_explanation_fragment, container, false
        );

        final Bundle args = getArguments();
        final TextView titleText = (TextView) rootView.findViewById(R.id.explanation_title);
        final TextView descriptionText = (TextView) rootView.findViewById(R.id.explanation_description);
        final ViewGroup modulesFrame = (ViewGroup) rootView.findViewById(R.id.explanation_modules_frame);
        final Button playButton = (Button) rootView.findViewById(R.id.explanation_play_button);
        playButton.setVisibility(View.VISIBLE);

        titleText.setText(args.getString(EXPLANATION_TITLE));
        descriptionText.setText(args.getString(EXPLANATION_DESCRIPTION));

        recyclerView = (RecyclerView) inflater.inflate(R.layout.activity_modules, null, false);
        modulesFrame.addView(recyclerView);
        ModuleManager.getInstance().loadModules(
                getContext(),
                new OnModulesLoadedListener() {
                    @Override
                    public void onModulesLoaded(Module[] modules) {
                        ModulesActivity.initializeRecyclerView(modules, recyclerView, new ModuleClickListener() {
                            @Override
                            public void onClick(View sender, String moduleId) {

                            }
                        }, getContext());
                        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                            @Override
                            public void onChildViewAttachedToWindow(View view) {
                                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                                lp.setMargins(0, -200, 0, -200);
                                TextView moduleTitle = ((TextView) ((LinearLayout)view).getChildAt(2));
                                if (moduleTitle.getText().equals("Introduction to Healthy Nutrition")) {
                                    ModulesExplanationFragment.sharedElement = ((LinearLayout) view).getChildAt(1);
                                }
                                view.setScaleX(0.5f);
                                view.setScaleY(0.5f);
                            }

                            @Override
                            public void onChildViewDetachedFromWindow(View view) {

                            }
                        });
                    }
                }
        );
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(view.getContext(), LessonActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        ExplanationActivity.currentInstance,
                        ModulesExplanationFragment.sharedElement,
                        getString(R.string.mode_transition_name));
                intent.putExtra(Constants.LESSON_ID, "l1");
                intent.putExtra(Constants.LESSON_OVERVIEW, false);
                startActivity(intent, options.toBundle());
            }
        });

        return rootView;
    }
}
