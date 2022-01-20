package com.hasan.test.utils.bus;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public final class Bus {

    private static final Bus INSTANCE = new Bus();

    private final PublishSubject<BusEvent> publisher = PublishSubject.create();

    private final Map<Consumer<?>, Disposable> disposables = new HashMap<>();

    private Bus() {
    }

    public static Bus instance() {
        return INSTANCE;
    }

    public <T extends BusEvent> void publish(T value) {
        Log.e("TAG", "publish: " + value);
        publisher.onNext(value);
    }

    public <T extends BusEvent> void register(Class<T> type, EventBusObserver<T> observer) {
        Log.e("TAG", "register: " + type);
        register(type, observer, true);
    }

    public <T extends BusEvent> void register(Class<T> type, EventBusObserver<T> observer, boolean observeOnMainThread) {
        if (disposables.containsKey(observer))
            return;
        Disposable disposable = publisher.ofType(type)
                .subscribeOn(Schedulers.io())
                .observeOn(observeOnMainThread ? AndroidSchedulers.mainThread() : Schedulers.io())
                .subscribe(observer);
        disposables.put(observer, disposable);
    }

    public <T extends BusEvent> void unregister(EventBusObserver<T> observer) {
        Disposable disposable = disposables.get(observer);
        disposables.remove(observer);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
