package org.pma.nutrifami.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.SwipeCardManager;
import org.pma.nutrifami.lib.SwipeCardStateContainer;
import org.pma.nutrifami.model.Unit;
import org.w3c.dom.Text;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeCardDataAdapter extends ArrayAdapter<SwipeCardData>{

    public SwipeCardDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SwipeCardData cardData = getItem(position);
        TextView questionView = (TextView) convertView.findViewById(R.id.swipe_card_text);
        switch (cardData.getCardType()) {
            case Question:
                questionView.setText(cardData.getUnit().getQuestion());
                break;
            case Explanation:
                questionView.setText(cardData.getUnit().getAnswerExplanation());
                questionView.setVisibility(View.INVISIBLE);
                break;
        }
        SwipeCardManager.getInstance().addContainer(new SwipeCardStateContainer(convertView, cardData));
        return convertView;
    }
}
