package com.hasan.test.data.remote;


import androidx.annotation.NonNull;

import com.hasan.test.data.exception.NoInternetException;
import com.hasan.test.data.remote.networkchecker.INetworkChecker;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class RemoteSingleOnSubscribe<T> implements SingleOnSubscribe<T> {

    private final INetworkChecker networkChecker;
    private final OnConnected<T> onConnected;

    public RemoteSingleOnSubscribe(INetworkChecker networkChecker, OnConnected<T> onConnected) {
        this.onConnected = onConnected;
        this.networkChecker = networkChecker;
    }

    @Override
    public void subscribe(@NonNull SingleEmitter<T> emitter) {
        if (networkChecker.isConnected()) {
            try {
                onConnected.onConnected(emitter);
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(new NoInternetException());
            }
        } else {
            emitter.onError(new NoInternetException());
        }
    }

    public interface OnConnected<T> {
        void onConnected(SingleEmitter<T> emitter) throws Exception;
    }

}
