package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{
    public static final String READ_COMMUNITY = "public";
    public static final int    mSNMPVersion = 0; // 0 represents SNMP version=1
    public static final String BASE_IP = "131.204.116.";
    public static final String OID_BASE_LEVEL = "1.3.6.1.2.1.43.11.1.1.9.";
    public static final String OID_BLACK = "1.1";
    public static final String OID_YELLOW = "1.2";
    public static final String OID_CYAN = "1.3";
    public static final String OID_MAGENTA = "1.4";
    private static final int RESULT_SETTINGS = -1;

    Button submit;
    TextView outputText;
    PrintStatus prntStat = new PrintStatus();
    AsyncTask theTask = new printTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        // Display the fragment as the main content.
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager
                .beginTransaction();
        PrefsFragment mPrefsFragment = new PrefsFragment();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
     */
        outputText = (TextView) findViewById(R.id.printDetails);
        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new printTask().execute();
            }
        });
    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    private class printTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            EditText ipEnd = (EditText) findViewById(R.id.ipEnd);
            EditText name = (EditText) findViewById(R.id.printName);
            String useIP = ipEnd.getText().toString();
            String  returnVal  = name.getText().toString() + "\n";
                    returnVal += prntStat.snmpGet(BASE_IP+useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL+OID_BLACK);
                    returnVal += prntStat.snmpGet(BASE_IP+useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL+OID_CYAN);
                    returnVal += prntStat.snmpGet(BASE_IP+useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL+OID_MAGENTA);
                    returnVal += prntStat.snmpGet(BASE_IP+useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL+OID_YELLOW);
            return returnVal;
        }
        protected void onPostExecute(String str)
        {
            outputText.setText(str);
        }
    }

}
