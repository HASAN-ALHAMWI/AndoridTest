package com.hasan.test.presentation.ui.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.hasan.test.BR;
import com.hasan.test.R;
import com.hasan.test.databinding.ActivityMainBinding;
import com.hasan.test.presentation.ui.base.view.activity.MVVMActivity;
import com.hasan.test.presentation.ui.main.adapter.ArticleListAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends MVVMActivity<MainViewModel, ActivityMainBinding> {

    private ArticleListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel provideViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupList();
    }

    private void setupList() {
        mAdapter = new ArticleListAdapter(mContext);
        mActivityBinding.rvList.setAdapter(mAdapter);
    }
}