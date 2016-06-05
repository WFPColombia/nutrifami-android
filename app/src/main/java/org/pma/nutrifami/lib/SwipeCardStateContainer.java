package org.pma.nutrifami.lib;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.adapter.SwipeCardData;
import org.pma.nutrifami.adapter.SwipeCardType;
import org.pma.nutrifami.model.Unit;

import java.lang.ref.WeakReference;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeCardStateContainer {
    private WeakReference<View> mRootCardView;
    private SwipeCardData mSwipeCardData;

    public SwipeCardStateContainer(View rootCardView, SwipeCardData swipeCardData) {
        this.mRootCardView = new WeakReference<>(rootCardView);
        this.mSwipeCardData = swipeCardData;
    }

    public void updateView() {
        View rootCardView = this.mRootCardView.get();
        if (this.mSwipeCardData.getCardType() == SwipeCardType.Explanation) {
            Animation fadeInAnimation = AnimationUtils.loadAnimation(rootCardView.getContext(), R.anim.fadein);
            TextView textView = (TextView) rootCardView.findViewById(R.id.swipe_card_text);

            textView.setVisibility(View.VISIBLE);
            textView.startAnimation(fadeInAnimation);
        }
    }
}
