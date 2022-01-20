package com.hasan.test.presentation.ui.base.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hasan.test.presentation.ui.callback.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

abstract public class BaseListAdapter<T, VH extends BaseViewHolder<T, ?>>
        extends RecyclerView.Adapter<VH> {


    public Context context;
    protected OnItemClickListener<T> onItemClickListener;
    protected String storageUrl;
    private List<T> data;

    public BaseListAdapter(Context context) {
        this.context = context;
    }

    public BaseListAdapter(Context context, OnItemClickListener<T> onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public BaseListAdapter(Context context, String storageUrl, OnItemClickListener<T> onItemClickListener) {
        this.context = context;
        this.storageUrl = storageUrl;
        this.onItemClickListener = onItemClickListener;
    }

    public BaseListAdapter(Context context, String storageUrl) {
        this.context = context;
        this.storageUrl = storageUrl;
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        T item = data != null && data.size() > position ? data.get(position) : null;
        holder.onBind(item, position);
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public List<T> getData() {
        return data;
    }

    // Clear previous data, then add the new data
    public void submitData(List<T> data) {
        if (data == null)
            return;
        if (this.data == null)
            this.data = new ArrayList<>();
        int size = this.data.size();
        this.data.clear();
        this.data.addAll(data);
        if (this.data.size() > size) {
            int itemCount = this.data.size() - size;
            notifyItemRangeInserted(size, itemCount);
            notifyItemRangeChanged(size, itemCount);
        } else {
            notifyItemRangeChanged(0, size);
        }
    }

    // Add the data to old data
    public void insertData(List<T> data) {
        if (data == null)
            return;
        if (this.data == null)
            this.data = new ArrayList<>();
        this.data.clear();
        this.data.addAll(data);
        notifyItemRangeInserted(0, data.size());
    }

    public void insertItem(T item) {
        if (this.data == null)
            this.data = new ArrayList<>();
        this.data.add(item);
        notifyItemInserted(data.size() - 1);
    }

    public void insertItemInIndex(T item, int index) {
        if (index < 0) return;
        if (this.data == null)
            this.data = new ArrayList<>();
        if (index < this.data.size()) {
            this.data.add(index, item);
        } else
            this.data.add(item);
        notifyItemInserted(index);
    }

    public void clearData() {
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                removeItem(data.get(i));
            }
        }
    }


    public void removeItem(T item) {
        if (item == null || data == null) return;
        int index = data.indexOf(item);
        if (index == -1) return; // not found
        data.remove(index);
        notifyItemRemoved(index);
        notifyItemChanged(index);
    }

    public void removeItem(int index) {
        if (index == -1) return; // not found
        data.remove(index);
        notifyItemRemoved(index);
        notifyItemChanged(index);
    }

    public boolean isEmptyData() {
        return data == null || data.isEmpty();
    }
}







