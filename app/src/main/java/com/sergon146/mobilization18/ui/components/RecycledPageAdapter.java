package com.sergon146.mobilization18.ui.components;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.Queue;

public abstract class RecycledPageAdapter<VH extends RecycledPageAdapter.ViewHolder>
        extends PagerAdapter {
    private Queue<VH> destroyedItems = new LinkedList<>();

    @NonNull
    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position) {
        VH viewHolder = destroyedItems.poll();

        if (viewHolder != null) {
            container.addView(viewHolder.itemView);
            onBindViewHolder(viewHolder, position);
        } else {
            viewHolder = onCreateViewHolder(container);
            onBindViewHolder(viewHolder, position);
            container.addView(viewHolder.itemView);
        }

        return viewHolder;
    }

    @Override
    public final void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView(((VH) object).itemView);
        destroyedItems.add((VH) object);
    }

    @Override
    public final boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((VH) object).itemView == view;
    }

    public abstract VH onCreateViewHolder(ViewGroup parent);

    public abstract void onBindViewHolder(VH viewHolder, int position);

    public abstract static class ViewHolder {
        protected final View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }
    }
}
