package com.hasan.test.data.remote.interceptor;



import com.hasan.test.data.remote.networkchecker.NetworkCheckerImpl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class OfflineInterceptor implements Interceptor {

    private final NetworkCheckerImpl networkChecker;

    @Inject
    public OfflineInterceptor(NetworkCheckerImpl networkChecker) {
        this.networkChecker = networkChecker;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        if (!networkChecker.isConnected()) {
            int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return chain.proceed(request);
    }
}
