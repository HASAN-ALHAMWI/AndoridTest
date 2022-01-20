package com.hasan.test.data.executor;




import com.hasan.test.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ThreadExecutorImpl implements ThreadExecutor {

    @Inject
    public ThreadExecutorImpl() {
    }

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
