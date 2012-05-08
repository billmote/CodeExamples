package com.androidfu.codeexamples;

import com.androidfu.codeexamples.model.AppConstants;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class DeveloperDebugPreferencesActivity extends MainActivity {

	private static final String TAG = "DeveloperDebugPreferencesActivity";
	private boolean developer_debug_override;
	private SharedPreferences sp;
	private OnSharedPreferenceChangeListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		developer_debug_override = AppConstants.getDebug(this);

		sp = PreferenceManager.getDefaultSharedPreferences(this);
		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
				developer_debug_override = AppConstants.getDebug(DeveloperDebugPreferencesActivity.this);
				Toast.makeText(DeveloperDebugPreferencesActivity.this, key, Toast.LENGTH_LONG).show();
			}
		};
		sp.registerOnSharedPreferenceChangeListener(listener);

		if (developer_debug_override) {
			/*
			 * This will show only when pushed from the PC to your phone and if
			 * the debug flag is set to true in app preferences.
			 * 
			 * Note the absence of BuildConfig.DEBUG as there's no way for you
			 * to have developer_debug_override set to true without that
			 * shared preference.
			 */
			Log.d(TAG, "Some arbitrary text");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (BuildConfig.DEBUG && developer_debug_override) {
			/*
			 * This will show only when pushed from the PC to your phone and if
			 * the debug flag is set to true in app preferences.
			 * 
			 * The BuildConfig.DEBUG isn't necessary, but I like it as a failsafe
			 * mechanism in case the default return for developer_debug_override
			 * were mistakenly changed to true ;)
			 */
			Toast.makeText(DeveloperDebugPreferencesActivity.this, "Debug set to true in preferences.", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		sp.unregisterOnSharedPreferenceChangeListener(listener);
	}
}
