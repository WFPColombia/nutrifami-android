package org.pma.nutrifami.view.viewholder;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.pma.nutrifami.view.container.PairDataContainer;
import org.pma.nutrifami.view.listener.PairClickListener;

/**
 * Created by juras on 11-Jun-16.
 */

public class PairViewHolder extends RecyclerView.ViewHolder{
    private final CardView mCardView;
    private final TextView mTextView;
    private final PairClickListener mOnClickListener;

    public PairViewHolder(CardView cardView, TextView textView, PairClickListener onClickListener) {
        super(cardView);
        this.mCardView = cardView;
        this.mTextView = textView;
        this.mOnClickListener = onClickListener;
    }

    public void update(PairDataContainer pairDataContainer) {
        if (this.mTextView == null || this.mCardView == null) {
            return;
        }

        final String pairPart = pairDataContainer.getPairPart();
        this.mTextView.setText(pairPart);
        View.OnClickListener onClickListener = null;
        switch (pairDataContainer.getPairPartState()) {
            case Incorrect:
            case Unselected:
            case Selected:
                onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnClickListener.onClick(pairPart);
                    }
                };
                break;
        }
        this.mCardView.setOnClickListener(onClickListener);
        this.mTextView.setOnClickListener(onClickListener);

        switch (pairDataContainer.getPairPartState()) {
            case Disabled:
                this.mCardView.setCardBackgroundColor(Color.WHITE);
                this.mTextView.setTextColor(Color.BLACK);
                this.mCardView.setAlpha(0.5f);
                break;
            case Correct:
                this.mCardView.setCardBackgroundColor(Color.GREEN);
                this.mTextView.setTextColor(Color.WHITE);
                this.mCardView.setAlpha(0.8f);
                break;
            case Incorrect:
                this.mCardView.setCardBackgroundColor(Color.RED);
                this.mTextView.setTextColor(Color.WHITE);
                this.mCardView.setAlpha(1f);
                break;
            case Unselected:
                this.mCardView.setCardBackgroundColor(Color.WHITE);
                this.mTextView.setTextColor(Color.BLACK);
                this.mCardView.setAlpha(1f);
                break;
            case Selected:
                this.mCardView.setCardBackgroundColor(Color.YELLOW);
                this.mTextView.setTextColor(Color.BLACK);
                this.mCardView.setAlpha(1f);
                break;
        }
    }
}
