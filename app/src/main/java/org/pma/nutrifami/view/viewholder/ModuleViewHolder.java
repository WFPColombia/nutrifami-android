package org.pma.nutrifami.view.viewholder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.lib.SessionManager;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.view.listener.ModuleClickListener;

import java.util.Locale;

import static android.graphics.Color.GRAY;
import static org.pma.nutrifami.util.Color.*;

/**
 * Created by pjura on 11-Jun-16.
 */

public class ModuleViewHolder extends RecyclerView.ViewHolder {
    private final Button mButton;
    private final TextView mTitleTextView;
    private final ModuleClickListener mModuleClickListener;
    private String mModuleId;
    private final ImageView mCompletedImageView;
    private final ImageView mModuleImageView;

    public ModuleViewHolder(View itemView, Button button, TextView titleTextView, ImageView completedImageView, ImageView moduleImageView, ModuleClickListener moduleClickListener) {
        super(itemView);
        this.mButton = button;
        this.mModuleClickListener = moduleClickListener;
        this.mTitleTextView = titleTextView;
        this.mCompletedImageView = completedImageView;
        this.mModuleImageView = moduleImageView;

        this.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModuleClickListener.onClick(v, mModuleId);
            }
        });
    }

    public void initialize(Module module) {
        this.mModuleId = module.getId();
        this.mTitleTextView.setText(module.getTitle());
        this.mButton.setText(String.format(Locale.getDefault(), "%d", ModuleManager.getInstance().getModuleOrder(module)));
        this.mModuleImageView.setImageResource(module.getImage());

        if (!module.getEnabled()) {
            this.mButton.setEnabled(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.mButton.setBackgroundTintList(ColorStateList.valueOf(GRAY));
            }
        } else {
            this.mButton.setEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.mButton.setBackgroundTintList(ColorStateList.valueOf(getAccentColor()));
            }

            if (SessionManager.getInstance().isModuleCompleted(this.mCompletedImageView.getContext(), module)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final float elevation = mButton.getContext().getResources().getDimension(R.dimen.module_elevation);
                    this.mButton.setElevation(elevation);
                    this.mCompletedImageView.setElevation(elevation);
                }
                mCompletedImageView.setVisibility(View.VISIBLE);
            }
        }
    }
}
