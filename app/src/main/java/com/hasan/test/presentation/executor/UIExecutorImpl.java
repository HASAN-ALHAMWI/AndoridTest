package com.hasan.test.presentation.executor;



import com.hasan.test.domain.executor.UIExecutor;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UIExecutorImpl implements UIExecutor {

    @Inject
    public UIExecutorImpl() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
