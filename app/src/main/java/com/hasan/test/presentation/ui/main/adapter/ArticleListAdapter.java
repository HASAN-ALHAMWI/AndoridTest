package com.hasan.test.presentation.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.hasan.test.R;
import com.hasan.test.data.entity.Article;
import com.hasan.test.databinding.ListItemArticleBinding;
import com.hasan.test.presentation.ui.base.adapter.BaseViewHolder;
import com.hasan.test.presentation.ui.base.adapter.PagingBaseListAdapter;


public class ArticleListAdapter extends PagingBaseListAdapter<Article,
        BaseViewHolder<Article, ListItemArticleBinding>> {


    public ArticleListAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder<Article, ListItemArticleBinding> provideItemViewHolder(ViewGroup parent) {
        ListItemArticleBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.list_item_article,
                parent,
                false);
        return new ArticleViewHolder(binding);
    }


    public static class ArticleViewHolder extends BaseViewHolder<Article, ListItemArticleBinding> {

        public ArticleViewHolder(@NonNull ListItemArticleBinding itemView) {
            super(itemView);
        }

        @Override
        public void onBind(Article item, int position) {
            binding.setData(item);
            binding.executePendingBindings();
        }
    }
}
