package com.hasan.test.domain.executor;

import io.reactivex.Scheduler;

public interface ThreadExecutor {
    Scheduler getScheduler();
}
