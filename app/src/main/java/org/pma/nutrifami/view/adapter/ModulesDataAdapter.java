package org.pma.nutrifami.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.view.listener.ModuleClickListener;
import org.pma.nutrifami.view.viewholder.ModuleViewHolder;

/**
 * Created by pjura on 11-Jun-16.
 */

public class ModulesDataAdapter extends RecyclerView.Adapter<ModuleViewHolder>{
    private final ModuleClickListener mOnClickListener;
    private final Module[] mModules;

    public ModulesDataAdapter(ModuleClickListener onClickListener, Module[] modules) {
        this.mOnClickListener = onClickListener;
        this.mModules = modules;
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.module_view, parent, false);

        final ImageButton button = (ImageButton) view.findViewById(R.id.module_button);
        final TextView titleTextView = (TextView) view.findViewById(R.id.module_title);
        final ImageView completedImageView = (ImageView) view.findViewById(R.id.module_completed);

        return new ModuleViewHolder(view, button, titleTextView, completedImageView, this.mOnClickListener);
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder holder, int position) {
        final Module module = this.mModules[position];
        holder.initialize(module);
    }

    @Override
    public int getItemCount() {
        return this.mModules.length;
    }
}
