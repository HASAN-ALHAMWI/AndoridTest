package com.hasan.test;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import dagger.hilt.android.HiltAndroidApp;
import io.reactivex.plugins.RxJavaPlugins;

@HiltAndroidApp
public class ApplicationClass extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        disableNightMode();
        RxJavaPlugins.setErrorHandler(error -> {
        });
    }


    private void disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}