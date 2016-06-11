package org.pma.nutrifami.view.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

import org.pma.nutrifami.R;
import org.pma.nutrifami.util.Updatable;

/**
 * Created by Peter Juras on 10.06.16.
 */

public class LessonProgressFragment extends Fragment implements Updatable {
    @Override
    public void update() {
        final View rootView = getView();
        if (rootView == null) {
            return;
        }
        final View completedImageView = rootView.findViewById(R.id.card_completed_image_view);
        if (completedImageView == null) {
            return;
        }
        completedImageView.setVisibility(View.VISIBLE);
    }
}
