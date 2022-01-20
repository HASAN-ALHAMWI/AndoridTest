package com.hasan.test.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hasan.test.presentation.ui.base.adapter.BaseListAdapter;

import java.util.List;

/**
 * This class is used to fill data in the Adapter and cache an image
 */
public class ViewsAdapter {

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String url) {
        Context context = imageView.getContext();
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .into(imageView);

    }


    @BindingAdapter("loadData")
    public static void loadData(RecyclerView recyclerView, List data) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            if (adapter instanceof BaseListAdapter) {
                System.out.println("-----data---- " + data.size());
                ((BaseListAdapter) adapter).submitData(data);
            }
        }
    }

}
