package com.hasan.test.domain.interactor.usecase.base;


import com.hasan.test.domain.executor.ThreadExecutor;
import com.hasan.test.domain.executor.UIExecutor;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<T> extends BaseUseCase {
    public UseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor) {
        super(uiExecutor, threadExecutor);
    }

    protected abstract Observable<T> buildObservable();

    public void execute(DisposableObserver<T> disposableObserver) {
        addDisposable(
                buildObservable()
                        .doOnError(Throwable::printStackTrace)
                        .subscribeOn(threadExecutor.getScheduler())
                        .observeOn(uiExecutor.getScheduler())
                        .subscribeWith(disposableObserver)
        );
    }
}
