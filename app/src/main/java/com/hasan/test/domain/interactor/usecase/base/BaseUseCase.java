package com.hasan.test.domain.interactor.usecase.base;



import com.hasan.test.domain.executor.ThreadExecutor;
import com.hasan.test.domain.executor.UIExecutor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseUseCase {

    private final CompositeDisposable compositeDisposable;
    protected UIExecutor uiExecutor;
    protected ThreadExecutor threadExecutor;

    public BaseUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor) {
        this.uiExecutor = uiExecutor;
        this.threadExecutor = threadExecutor;
        compositeDisposable = new CompositeDisposable();
    }

    protected void addDisposable(Disposable disposable) {
        if (disposable != null)
            compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
