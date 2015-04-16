package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class UserSettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SettingsFragment())
                    .commit();
        }
    }

    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);

        int modNumber = sharedPref.getInt(getString(R.string.pref_mod), 0);
        modNumber += 1;


        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.pref_mod), modNumber);
        editor.commit();
    }


    public static class SettingsFragment extends PreferenceFragment {

        public SettingsFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

        }
    }
}
