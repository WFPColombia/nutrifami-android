package org.pma.nutrifami.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.listener.AnswerClickListener;

/**
 * Created by Peter on 09.06.2016.
 */
public class QuizAnswersDataAdapter extends RecyclerView.Adapter<QuizAnswersDataAdapter.QuizAnswersViewHolder> {
    public static class QuizAnswersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private CardView mCardView;
        private int mPosition;
        private AnswerClickListener mAnswerClickListener;

        public QuizAnswersViewHolder(CardView cardView, TextView textView, AnswerClickListener answerClickListener) {
            super(cardView);

            this.mCardView = cardView;
            this.mCardView.setOnClickListener(this);

            this.mTextView = textView;
            this.mTextView.setOnClickListener(this);

            this.mAnswerClickListener = answerClickListener;
        }

        public void setText(String text) {
            if (this.mTextView != null) {
                this.mTextView.setText(text);
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

    private String[] mAnswers;
    private AnswerClickListener mAnswerClickListener;

    public QuizAnswersDataAdapter(AnswerClickListener answerClickListener) {
        this.mAnswerClickListener = answerClickListener;
    }

    public void setAnswers(String[] answers) {
        this.mAnswers = answers;
        this.notifyDataSetChanged();
    }

    @Override
    public QuizAnswersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.quiz_answer, parent, false);

        CardView cardView = (CardView) view.findViewById(R.id.quiz_answer_card);
        TextView textView = (TextView) view.findViewById(R.id.quiz_answer_text);

        final QuizAnswersViewHolder viewHolder = new QuizAnswersViewHolder(cardView, textView, this.mAnswerClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuizAnswersViewHolder holder, int position) {
        holder.setText(this.mAnswers[position]);
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return this.mAnswers.length;
    }
}
