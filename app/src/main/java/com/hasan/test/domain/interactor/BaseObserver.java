package com.hasan.test.domain.interactor;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

public class BaseObserver<T> extends DisposableObserver<T> {

    Consumer<? super T> onNext;
    Consumer<? super Throwable> onError;
    Action onComplete;

    public BaseObserver() {
    }

    public BaseObserver(Consumer<? super T> onNext) {
        this.onNext = onNext;
    }

    public BaseObserver(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
        this.onNext = onNext;
        this.onError = onError;
    }

    public BaseObserver(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        this.onNext = onNext;
        this.onError = onError;
        this.onComplete = onComplete;
    }

    @Override
    public void onNext(T t) {
        if (onNext != null)
            try {
                onNext.accept(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void onError(Throwable e) {
        if (onError != null) {
            try {
                onError.accept(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {
        if (onComplete != null) {
            try {
                onComplete.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
