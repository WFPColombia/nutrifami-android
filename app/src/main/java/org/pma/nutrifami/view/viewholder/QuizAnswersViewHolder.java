package org.pma.nutrifami.view.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.pma.nutrifami.view.listener.AnswerClickListener;

/**
 * Created by pjura on 11-Jun-16.
 */

public class QuizAnswersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Button mButton;
        private final AnswerClickListener mAnswerClickListener;
        private int mPosition;

        public QuizAnswersViewHolder(Button button, AnswerClickListener answerClickListener) {
            super(button);

            this.mButton = button;
            this.mButton.setOnClickListener(this);

            this.mAnswerClickListener = answerClickListener;
        }

        public void setText(String text) {
            if (this.mButton != null) {
                this.mButton.setText(text);
            }
        }

        public void setPosition(int position) {
            this.mPosition = position;
        }

        @Override
        public void onClick(View v) {
            this.mAnswerClickListener.onAnswerClicked(this.mPosition);
        }
    }
