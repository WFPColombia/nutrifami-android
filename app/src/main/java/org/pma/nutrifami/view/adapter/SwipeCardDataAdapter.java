package org.pma.nutrifami.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.model.unit.SwipeUnit;
import org.pma.nutrifami.model.unit.Unit;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeCardDataAdapter extends ArrayAdapter<Unit>{

    public SwipeCardDataAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SwipeUnit unit = (SwipeUnit) getItem(position);
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.swipe_card_image);
        final TextView questionView = (TextView) convertView.findViewById(R.id.swipe_card_text);
        imageView.setImageResource(unit.getImage());
        questionView.setText(unit.getQuestion());
        return convertView;
    }
}
