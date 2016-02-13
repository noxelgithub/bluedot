package com.example.neshat.bluedot.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;

public class PreferenceData {

    /** The m preferences. */
    private SharedPreferences mPreferences;

    /** The m editor. */
    private SharedPreferences.Editor mEditor;

    /** The Constant PREFERENCE_NAME. */
    private static final String PREFERENCE_NAME = "save_pref";

    /**
     * Instantiates a new preference data.
     *
     * @param context
     *            the context
     */
    public PreferenceData(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    /**
     * Store string in preference.
     *
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public void storeStringInPreference(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * Gets the string from preference.
     *
     * @param key
     *            the key
     * @return the string from preference
     */
    public String getStringFromPreference(String key) {
        return mPreferences.getString(key, null);
    }

    /**
     * Clear all preference.
     */
    public void clearAllPreference() {
        mEditor.clear();
        mEditor.commit();
    }

}