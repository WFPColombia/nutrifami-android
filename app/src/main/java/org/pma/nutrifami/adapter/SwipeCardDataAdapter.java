package org.pma.nutrifami.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.model.Unit;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeCardDataAdapter extends ArrayAdapter<Unit>{

    public SwipeCardDataAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Unit unit = getItem(position);
        final TextView questionView = (TextView) convertView.findViewById(R.id.swipe_card_text);
        questionView.setText(unit.getQuestion());
        return convertView;
    }
}
