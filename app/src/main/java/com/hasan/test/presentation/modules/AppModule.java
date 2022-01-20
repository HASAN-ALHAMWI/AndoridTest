package com.hasan.test.presentation.modules;

import android.content.Context;


import com.hasan.test.data.executor.ThreadExecutorImpl;
import com.hasan.test.data.local.SharedPreferencesManager;
import com.hasan.test.data.remote.networkchecker.INetworkChecker;
import com.hasan.test.data.remote.networkchecker.NetworkCheckerImpl;
import com.hasan.test.domain.executor.ThreadExecutor;
import com.hasan.test.domain.executor.UIExecutor;
import com.hasan.test.presentation.executor.UIExecutorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * this class is used to inject ApplicationClass .
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public SharedPreferencesManager provideSharedPref(@ApplicationContext Context context) {
        return new SharedPreferencesManager(context);
    }

    @Provides
    @Singleton
    Context provideContext(@ApplicationContext Context context) {
        return context;
    }


    @Singleton
    @Provides
    public INetworkChecker provideNetworkChecker(NetworkCheckerImpl networkChecker) {
        return networkChecker;
    }

    @Singleton
    @Provides
    public UIExecutor provideUIExecutor(UIExecutorImpl uiExecutor) {
        return uiExecutor;
    }

    @Singleton
    @Provides
    public ThreadExecutor provideThreadExecutor(ThreadExecutorImpl threadExecutor) {
        return threadExecutor;
    }
}