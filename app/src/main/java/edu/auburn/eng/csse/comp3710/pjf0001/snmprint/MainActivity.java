package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
    public static final String READ_COMMUNITY = "public";
    public static final int    mSNMPVersion = 0; // 0 represents SNMP version=1
    public static final String BASE_IP = "131.204.116.176";
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
        Log.i("DEBUG", "made it to onCreate");

        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new printTask().execute();
                //Log.i("DEBUG", "made it to onCreate submit");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class printTask extends AsyncTask<String, Void, Integer>{
        @Override
        protected Integer doInBackground(String... params) {
            Log.i("DEBUG", "made it to output");
            return prntStat.snmpGet(BASE_IP, READ_COMMUNITY, mSNMPVersion, OID_BASE_LEVEL+OID_BLACK);
        }
    }

}
