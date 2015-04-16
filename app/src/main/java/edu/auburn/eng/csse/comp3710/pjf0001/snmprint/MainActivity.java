package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
    public static final String READ_COMMUNITY = "public";
    public static final int    mSNMPVersion = 0; // 0 represents SNMP version=1
    public static final String BASE_IP = "131.204.116.";
    public static final String OID_BASE_LEVEL = "1.3.6.1.2.1.43.11.1.1.9.";
    public static final String OID_BLACK = "1.1";
    public static final String OID_YELLOW = "1.2";
    public static final String OID_CYAN = "1.3";
    public static final String OID_MAGENTA = "1.4";

    Button submit;
    TextView outputText;
    PrintStatus prntStat = new PrintStatus();
    AsyncTask theTask = new printTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputText = (TextView) findViewById(R.id.printDetails);

        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new printTask().execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
