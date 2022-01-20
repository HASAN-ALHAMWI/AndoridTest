package com.hasan.test.presentation.ui.common.adapter;

import androidx.annotation.NonNull;

import com.hasan.test.databinding.LayoutEmptyListBinding;
import com.hasan.test.presentation.ui.base.adapter.BaseViewHolder;


public class EmptyListViewHolder extends BaseViewHolder<String, LayoutEmptyListBinding> {

    private String title;

    public EmptyListViewHolder(@NonNull LayoutEmptyListBinding itemView) {
        super(itemView);
    }

    @Override
    public void onBind(String item, int position) {

    }
}
