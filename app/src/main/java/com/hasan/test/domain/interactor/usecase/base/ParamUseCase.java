package com.hasan.test.domain.interactor.usecase.base;


import com.hasan.test.domain.executor.ThreadExecutor;
import com.hasan.test.domain.executor.UIExecutor;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

abstract public class ParamUseCase<T, Param> extends BaseUseCase {
    public ParamUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor) {
        super(uiExecutor, threadExecutor);
    }

    abstract protected Observable<T> buildObservable(Param param);

    public void execute(DisposableObserver<T> observer, Param param) {
        addDisposable(
                buildObservable(param)
                        .doOnError(Throwable::printStackTrace)
                        .subscribeOn(threadExecutor.getScheduler())
                        .observeOn(uiExecutor.getScheduler())
                        .subscribeWith(observer)
        );
    }
}
