package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String READ_COMMUNITY = "public";
        final int    mSNMPVersion = 0; // 0 represents SNMP version=1
        final String BASE_IP = "131.204.116.";
        final String OID_BASE_LEVEL = "1.3.6.1.2.1.43.11.1.1.9.";
        final String OID_BLACK = "1.1";
        final String OID_YELLOW = "1.2";
        final String OID_CYAN = "1.3";
        final String OID_MAGENTA = "1.4";


        final PrintStatus stat = new PrintStatus();
        Button submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stat.snmpGet(BASE_IP+findViewById(R.id.ipEnd).toString(), READ_COMMUNITY, mSNMPVersion,OID_BASE_LEVEL+OID_BLACK);
                stat.snmpGet(BASE_IP+findViewById(R.id.ipEnd).toString(), READ_COMMUNITY, mSNMPVersion,OID_BASE_LEVEL+OID_YELLOW);
                stat.snmpGet(BASE_IP+findViewById(R.id.ipEnd).toString(), READ_COMMUNITY, mSNMPVersion,OID_BASE_LEVEL+OID_CYAN);
                stat.snmpGet(BASE_IP+findViewById(R.id.ipEnd).toString(), READ_COMMUNITY, mSNMPVersion,OID_BASE_LEVEL+OID_MAGENTA);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
