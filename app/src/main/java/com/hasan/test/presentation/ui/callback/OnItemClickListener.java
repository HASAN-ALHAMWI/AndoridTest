package com.hasan.test.presentation.ui.callback;

public interface OnItemClickListener<T> {
    void onClick(T item, int position);
}
