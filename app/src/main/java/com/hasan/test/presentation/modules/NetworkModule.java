package com.hasan.test.presentation.modules;



import static com.hasan.test.utils.constants.NetworkConstants.CACHE_SIZE;
import static com.hasan.test.utils.constants.NetworkConstants.TIME_OUT;

import android.content.Context;

import com.hasan.test.data.remote.api.ApiEndPoints;
import com.hasan.test.data.remote.api.RetrofitService;
import com.hasan.test.data.remote.interceptor.AuthInterceptor;
import com.hasan.test.data.remote.interceptor.OfflineInterceptor;
import com.hasan.test.data.remote.interceptor.OnlineInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiEndPoints.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static RetrofitService getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    @Provides
    @Singleton
    static OkHttpClient getRequestHeader(@ApplicationContext Context context,
                                         AuthInterceptor authInterceptor,
                                         OfflineInterceptor offlineInterceptor,
                                         OnlineInterceptor onlineInterceptor
                                         ) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        Cache myCache = new Cache(context.getCacheDir(), CACHE_SIZE);
        httpClient.cache(myCache);
        httpClient.addInterceptor(loggingInterceptor);
        //httpClient.addInterceptor(encryptionInterceptor);

        httpClient.addInterceptor(authInterceptor);
        httpClient.addInterceptor(offlineInterceptor);
        return httpClient.build();
    }
}