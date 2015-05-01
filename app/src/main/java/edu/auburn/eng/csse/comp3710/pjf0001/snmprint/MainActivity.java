package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public static final String READ_COMMUNITY = "public";
    public static final int mSNMPVersion = 0; // 0 represents SNMP version=1
    public static final String BASE_IP = "131.204.116.";
    public static final String OID_BASE_LEVEL = "1.3.6.1.2.1.43.11.1.1.9.";
    public static final String OID_BLACK = "1.1";
    public static final String OID_YELLOW = "1.2";
    public static final String OID_CYAN = "1.3";
    public static final String OID_MAGENTA = "1.4";

    TextView outputText;
    snmpServer prntStat = new snmpServer();

    final dbHandler db = new dbHandler(this);

    private static final int RESULT_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);

        outputText = (TextView) findViewById(R.id.printDetails);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addprinter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                AsyncTask theTask = new printTask().execute();
                break;

            case R.id.action_add_printer:
                Intent j = new Intent(this, printerController.class);
                startActivityForResult(j, RESULT_SETTINGS);
                break;
        }
        return true;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    private void showUserSettings() {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        StringBuilder builder = new StringBuilder();

        builder.append("\n Username: "
                + sharedPrefs.getString("prefUsername", "NULL"));

        builder.append("\n Send report:"
                + sharedPrefs.getBoolean("prefSendReport", false));

        builder.append("\n Sync Frequency: "
                + sharedPrefs.getString("prefSyncFrequency", "NULL"));

        builder.append("\n Mod Number: "
                + sharedPrefs.getInt("modified", 0));

        builder.append("\n\n Printer Name: "
                + sharedPrefs.getString("printerName", "NULL"));

        builder.append("\n IP Address: 131.204.116."
                + sharedPrefs.getString("ipEnd", "NULL"));

        TextView settingsTextView = (TextView) findViewById(R.id.textUserSettings);

        settingsTextView.setText(builder.toString());
    }
    */

    private class printTask extends AsyncTask<String, Void, String> {
        private final ProgressDialog pdialog = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            this.pdialog.setMessage("Getting Printer Status...");
            this.pdialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            ArrayList<Printer> pList = db.getAllPrinters();
            Printer p;
            String returnVal="";
            for(int i=0; i<pList.size();i++) {
                p = pList.get(i);
                String useIP = p.getIP();
                String name = p.getPrinterName();
                returnVal += "\n" + name + "\n";
                returnVal += prntStat.getPrintStatus(BASE_IP + useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL + OID_BLACK);
                returnVal += prntStat.getPrintStatus(BASE_IP + useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL + OID_CYAN);
                returnVal += prntStat.getPrintStatus(BASE_IP + useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL + OID_MAGENTA);
                returnVal += prntStat.getPrintStatus(BASE_IP + useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL + OID_YELLOW);
            }
            return returnVal;
        }
        @Override
        protected void onPostExecute(String str) {
           if (this.pdialog.isShowing()) {
               this.pdialog.dismiss();
           }
            outputText.setText(str);
        }
    }
}
