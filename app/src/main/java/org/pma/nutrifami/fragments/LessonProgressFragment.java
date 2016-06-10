package org.pma.nutrifami.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

import org.pma.nutrifami.R;
import org.pma.nutrifami.util.Updateable;

/**
 * Created by Peter Juras on 10.06.16.
 */

public class LessonProgressFragment extends Fragment implements Updateable {
    @Override
    public void update() {
        View rootView = getView();
        if (rootView == null) {
            return;
        }
        View completedImageView = rootView.findViewById(R.id.card_completed_image_view);
        if (completedImageView == null) {
            return;
        }
        completedImageView.setVisibility(View.VISIBLE);
    }
}
