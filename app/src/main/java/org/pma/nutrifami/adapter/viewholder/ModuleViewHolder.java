package org.pma.nutrifami.adapter.viewholder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.pma.nutrifami.lib.SessionManager;
import org.pma.nutrifami.listener.ModuleClickListener;
import org.pma.nutrifami.model.Module;

/**
 * Created by pjura on 11-Jun-16.
 */

public class ModuleViewHolder extends RecyclerView.ViewHolder {
    private final FloatingActionButton mButton;
    private final TextView mTitleTextView;
    private final ModuleClickListener mModuleClickListener;
    private String mModuleId;
    private final ImageView mCompletedImageView;

    public ModuleViewHolder(View itemView, FloatingActionButton button, TextView titleTextView, ImageView completedImageView, ModuleClickListener moduleClickListener) {
        super(itemView);
        this.mButton = button;
        this.mModuleClickListener = moduleClickListener;
        this.mTitleTextView = titleTextView;
        this.mCompletedImageView = completedImageView;

        this.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModuleClickListener.onClick(mModuleId);
            }
        });
    }

    public void initialize(Module module) {
        this.mModuleId = module.getId();
        this.mTitleTextView.setText(module.getTitle());

        if (!module.getEnabled()) {
            this.mButton.setEnabled(false);
            this.mButton.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.mButton.setElevation(0);
            }
        } else if (SessionManager.getInstance().isModuleCompleted(this.mCompletedImageView.getContext(), module)) {
            mCompletedImageView.setVisibility(View.VISIBLE);
        }
    }
}
