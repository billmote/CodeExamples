package com.androidfu.codeexamples;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Preferences extends PreferenceActivity {
    
    private static final String TAG = "Preferences";
	
	private boolean myBooleanPreference;
	private boolean developerBooleanPreference;
    private String ListPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPrefs();
        addPreferencesFromResource(R.xml.preferences);
        addPreferencesFromResource(R.xml.more_preferences);
        if (BuildConfig.DEBUG){
            addPreferencesFromResource(R.xml.debug_preferences);
        }
    }

    private void getPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        myBooleanPreference = prefs.getBoolean("my_boolean", true);
        ListPreference = prefs.getString("my_listpreference", "#FFFF0000");
        developerBooleanPreference = prefs.getBoolean("developer_debug", false);
    }
}
