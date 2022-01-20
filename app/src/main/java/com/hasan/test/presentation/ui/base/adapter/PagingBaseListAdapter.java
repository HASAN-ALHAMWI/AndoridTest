package com.hasan.test.presentation.ui.base.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.hasan.test.R;
import com.hasan.test.databinding.LayoutEmptyListBinding;
import com.hasan.test.databinding.LayoutLoadingPageBinding;
import com.hasan.test.presentation.ui.callback.OnItemClickListener;
import com.hasan.test.presentation.ui.callback.OnLoadingPageListener;
import com.hasan.test.presentation.ui.common.adapter.EmptyListViewHolder;
import com.hasan.test.presentation.ui.common.adapter.LoadingListViewHolder;

abstract public class PagingBaseListAdapter<T, VH extends BaseViewHolder<T, ?>>
        extends BaseListAdapter<T, BaseViewHolder<T, ?>> {

    public static final int FILLED = 1;
    public static final int LOADING = 2;
    public static final int EMPTY = 3;

    protected OnLoadingPageListener onLoadingPageListener;
    protected OnItemClickListener<T> onItemClickListener;

    public PagingBaseListAdapter(Context context) {
        super(context);
    }


    public PagingBaseListAdapter(Context context, OnLoadingPageListener onLoadingPageListener) {
        super(context);
        this.onLoadingPageListener = onLoadingPageListener;
    }

    public PagingBaseListAdapter(Context context, String storageUrl, OnLoadingPageListener onLoadingPageListener) {
        super(context, storageUrl);
        this.onLoadingPageListener = onLoadingPageListener;
    }

    public PagingBaseListAdapter(Context context, OnItemClickListener<T> onItemClickListener) {
        super(context);
        this.onItemClickListener = onItemClickListener;
    }

    public PagingBaseListAdapter(Context context, String storageUrl, OnItemClickListener<T> onItemClickListener) {
        super(context, storageUrl);
        this.onItemClickListener = onItemClickListener;
    }

    public PagingBaseListAdapter(Context context, OnItemClickListener<T> onItemClickListener,
                                 OnLoadingPageListener onLoadingPageListener) {
        super(context);
        this.onLoadingPageListener = onLoadingPageListener;
        this.onItemClickListener = onItemClickListener;
    }

    public PagingBaseListAdapter(Context context, String storageUrl, OnItemClickListener<T> onItemClickListener, OnLoadingPageListener onLoadingPageListener) {
        super(context, storageUrl);
        this.onLoadingPageListener = onLoadingPageListener;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T, ?> holder, int position) {
        if (getItemViewType(position) == FILLED) {
            super.onBindViewHolder(holder, position);
            if (position == getData().size() - 1 && onLoadingPageListener != null) {
                onLoadingPageListener.startLoading();
            }
        }
    }

    @NonNull
    @Override
    public BaseViewHolder<T, ?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == EMPTY) {
            LayoutEmptyListBinding binding =
                    DataBindingUtil.inflate(
                            LayoutInflater.from(context),
                            R.layout.layout_empty_list,
                            parent,
                            false);
            return (BaseViewHolder<T, ?>) new EmptyListViewHolder(binding);
        } else if (viewType == FILLED) {
            return provideItemViewHolder(parent);
        } else {
            LayoutLoadingPageBinding binding =
                    DataBindingUtil.inflate(
                            LayoutInflater.from(context),
                            R.layout.layout_loading_page,
                            parent,
                            false
                    );
            return (BaseViewHolder<T, ?>) new LoadingListViewHolder(binding);
        }
    }

    protected abstract VH provideItemViewHolder(ViewGroup parent);

    @Override
    public int getItemViewType(int position) {
        return (getData() != null && getData().isEmpty())
                ? EMPTY : (getData() != null && position < getData().size() && getData().get(position) == null)
                ? LOADING : FILLED;
    }

    @Override
    public int getItemCount() {
        if (getData() != null && getData().isEmpty())
            return 1;
        return super.getItemCount();
    }


    public void addBottomLoader() {
        getData().add(null); // add loader
        new Handler().post(() -> {
            notifyItemInserted(getData().size() - 1);
        });
    }

    public void removeBottomLoader() {
        if (this.getData().size() > 0) {
            int position = this.getData().size() - 1;
            T item = getData().get(position);
            if (item == null) {
                this.getData().remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void handleLoaderState(boolean isLoading) {
        if (isEmptyData())
            return;
        if (isLoading) {
            addBottomLoader();
        } else {
            removeBottomLoader();
        }
    }
}
