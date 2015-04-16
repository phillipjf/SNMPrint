package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class AddPrinterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprinter);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AddPrinterFragment())
                    .commit();
        }

    }

    protected void onPause(){
        super.onPause();
    }

    public static class AddPrinterFragment extends PreferenceFragment {

        public AddPrinterFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.printers);

        }
    }
}
