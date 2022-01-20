package com.hasan.test.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class SharedPreferencesManager {

    private static final String PREF_TOKEN = "com.hasan.test.pref_token";
    private static final String PREF_NAME = "com.hasan.test.pref_name";
    private static final String PREF_LANGUAGE = "com.hasan.test.pref_language";
    // Shared Preferences
    private final SharedPreferences mPreferences;
    private final SharedPreferences.Editor mEditor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    @Inject
    public SharedPreferencesManager(@ApplicationContext Context context) {
        this._context = context;
        mPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mPreferences.edit();
    }

    public static void clearPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }

    public String getLanguage() {
        return PreferenceManager.getDefaultSharedPreferences(_context)
                .getString(PREF_LANGUAGE, "");
    }

    public void setLanguage(String lang) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(_context).edit();
        editor.putString(PREF_LANGUAGE, lang);
        editor.apply();
    }


    public String getToken() {
        return mPreferences.getString(PREF_TOKEN, "");
    }

    public void setToken(String token) {
        mEditor.putString(PREF_TOKEN, token);
        mEditor.commit();
    }


}
