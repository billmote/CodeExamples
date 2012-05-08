package com.androidfu.codeexamples.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppConstants {

	private static final String TAG = "AppConstants";

	private AppConstants() {
		// Do not allow this to be instantiated.
	}

	public static boolean getDebug(Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getBoolean("developer_debug", false);

	}
}
