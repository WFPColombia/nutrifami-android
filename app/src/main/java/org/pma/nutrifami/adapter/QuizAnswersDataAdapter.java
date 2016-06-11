package org.pma.nutrifami.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.adapter.viewholder.QuizAnswersViewHolder;
import org.pma.nutrifami.listener.AnswerClickListener;

/**
 * Created by Peter on 09.06.2016.
 */
public class QuizAnswersDataAdapter extends RecyclerView.Adapter<QuizAnswersViewHolder> {


    private final AnswerClickListener mAnswerClickListener;
    private String[] mAnswers;

    public QuizAnswersDataAdapter(AnswerClickListener answerClickListener) {
        this.mAnswerClickListener = answerClickListener;
    }

    public void setAnswers(String[] answers) {
        this.mAnswers = answers;
        this.notifyDataSetChanged();
    }

    @Override
    public QuizAnswersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.quiz_answer, parent, false);

        final CardView cardView = (CardView) view.findViewById(R.id.quiz_answer_card);
        final TextView textView = (TextView) view.findViewById(R.id.quiz_answer_text);

        return new QuizAnswersViewHolder(cardView, textView, this.mAnswerClickListener);
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
