package com.hasan.test.data.remote.interceptor;

import android.util.Log;

import com.hasan.test.data.local.SharedPreferencesManager;
import com.hasan.test.data.remote.api.ApiEndPoints;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class AuthInterceptor implements Interceptor {

    private final SharedPreferencesManager sharedPref;

    @Inject
    public AuthInterceptor(SharedPreferencesManager sharedPref) {
        this.sharedPref = sharedPref;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request newRequest = originalRequest.newBuilder()
                .addHeader(ApiEndPoints.ACCEPT_HEADER, "application/json")
                .addHeader(ApiEndPoints.LANGUAGE_HEADER, sharedPref.getLanguage())
                .addHeader(ApiEndPoints.DEVICE_HEADER, "android")
                .addHeader(ApiEndPoints.TOKEN_HEADER, "Bearer " + sharedPref.getToken())
                .build();
        Response response = chain.proceed(newRequest);
        Log.d("Authorization", sharedPref.getToken());
        return response;
    }

}
