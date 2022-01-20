package com.hasan.test.presentation.ui.base.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.hasan.test.presentation.ui.base.view.viewmodel.BaseViewModel;
import com.hasan.test.utils.lifecycle.EventObserver;


abstract public class MVVMActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseActivity {


    public DB mActivityBinding;

    protected VM mViewModel;


    abstract protected VM provideViewModel();

    abstract protected int getViewModelId();


    // Called to get params from intent's bundle
    protected void onFetchParams() {
    }

    @Override
    protected void setView() {
        if (getLayoutId() != 0)
            mActivityBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDataBinding();
        onFetchParams();
        initToolbar();
        setupBaseObservers();
    }


    private void setupDataBinding() {
        mViewModel = provideViewModel();
        mActivityBinding.setVariable(getViewModelId(), mViewModel);
        mActivityBinding.setLifecycleOwner(this);
        mActivityBinding.executePendingBindings();
    }

    protected void setupBaseObservers() {
        mViewModel.toastMessage.observe(this, new EventObserver<>(this::showMessage));
        mViewModel.toastMessageResource.observe(this, new EventObserver<>(this::showMessage));
        mViewModel.hideKeyboard.observe(this, new EventObserver<>(ignored -> hideKeyboard()));
    }
}