package com.hasan.test.presentation.ui.base.view.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.ConfigurationCompat;


import com.hasan.test.data.local.SharedPreferencesManager;
import com.hasan.test.utils.constants.DataConstants;

import java.util.Locale;

abstract public class LocalizationActivity extends AppCompatActivity {

    public SharedPreferencesManager mSharedPreferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferencesManager = new SharedPreferencesManager(this);
        String lang = mSharedPreferencesManager.getLanguage();

        changeLang(lang);
    }


    public void changeLang(String lang) {
        Locale mLocale;
        Configuration configuration = getResources().getConfiguration();
        String localeLang = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0).getLanguage();

        if (lang.equals("")) {
            lang = localeLang;
            new SharedPreferencesManager(this).setLanguage(localeLang);
        }

        if (lang.equals(DataConstants.LANGUAGE_AR))
            mLocale = new Locale("ar", "SY");
        else
            mLocale = new Locale(lang);

        Locale.setDefault(mLocale);
        configuration.setLayoutDirection(new Locale(lang));
        configuration.setLocale(mLocale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    @Override
    protected void onResume() {
        super.onResume();
        String lang = mSharedPreferencesManager.getLanguage();

        changeLang(lang);
    }
}
