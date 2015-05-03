package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity{
    public static final String READ_COMMUNITY = "public";
    public static final int mSNMPVersion = 0; // 0 represents SNMP version=1
    public static final String BASE_IP = "131.204.116.";
    public static final String OID_BASE_LEVEL = "1.3.6.1.2.1.43.11.1.1.9.";
    public static final String OID_BLACK = "1.1";
    public static final String OID_YELLOW = "1.2";
    public static final String OID_CYAN = "1.3";
    public static final String OID_MAGENTA = "1.4";
    private static final int RESULT_SETTINGS = 1;

    snmpServer prntStat = new snmpServer();
    final dbHandler db = new dbHandler(this);
    ArrayList<Printer> pList;
    LinearLayout linearChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);

        pList = db.getAllPrinters();
        linearChart = (LinearLayout)findViewById(R.id.printDetails);
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

    public void drawChart(int color, int height) {
        System.out.println("Color: "+ color+ " " + "Height: " + height);
        switch (color){
            case 0:
                color = Color.BLACK;
                break;
            case 1:
                color = Color.CYAN;
                break;
            case 2:
                color = Color.MAGENTA;
                break;
            case 3:
                color = Color.YELLOW;
                break;
            case 4:
                color = Color.TRANSPARENT;
                break;
        }
        if(height<=0){color=Color.RED;height = 100;}

        View view = new View(MainActivity.this);
        view.setBackgroundColor(color);
        view.setLayoutParams(new LinearLayout.LayoutParams(height, 50));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.setMargins(3, 2, 2, 2); // substitute parameters for left, top, right, bottom
        view.setLayoutParams(params);
        linearChart.addView(view);
    }

    private class printTask extends AsyncTask<String, Void, String> {
        private final ProgressDialog pdialog = new ProgressDialog(MainActivity.this);
        TextView title;
        Printer p;

        @Override
        protected void onPreExecute() {
            this.pdialog.setMessage("Getting Printer Status...");
            this.pdialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String returnVal="";

            for(int i=0; i< pList.size();i++) {
                p = pList.get(i);
                String useIP = p.getIP();
                returnVal = p.getPrinterName();
                p.setkVal(prntStat.getPrintStatus(BASE_IP + useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL + OID_BLACK));
                p.setcVal(prntStat.getPrintStatus(BASE_IP + useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL + OID_CYAN));
                p.setmVal(prntStat.getPrintStatus(BASE_IP + useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL + OID_MAGENTA));
                p.setyVal(prntStat.getPrintStatus(BASE_IP + useIP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL + OID_YELLOW));
                db.updatePrinter(p);

            }
            return returnVal;
        }
        @Override
        protected void onPostExecute(String str) {
            linearChart.removeAllViews();
            if (this.pdialog.isShowing()) {
                this.pdialog.dismiss();
            }
            for(int i=0; i<pList.size(); i++) {
                p = pList.get(i);
                title = new TextView(MainActivity.this);
                title.setText(p.getPrinterName());
                linearChart.addView(title);
                drawChart(0, p.getkVal() * 10);
                drawChart(1, p.getcVal() * 10);
                drawChart(2, p.getmVal() * 10);
                drawChart(3, p.getyVal() * 10);
                drawChart(4, 1);
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Printer p;
        TextView title;
        for(int i=
            0; i<pList.size(); i++) {
            p = pList.get(i);
            title = new TextView(MainActivity.this);
            title.setText(p.getPrinterName());
            linearChart.addView(title);
            drawChart(0, p.getkVal() * 10);
            drawChart(1, p.getcVal() * 10);
            drawChart(2, p.getmVal() * 10);
            drawChart(3, p.getyVal() * 10);
            drawChart(4, 1);
        }
    }

}
