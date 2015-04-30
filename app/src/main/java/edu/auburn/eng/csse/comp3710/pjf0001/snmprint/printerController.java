package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class printerController extends Activity {

    Button addPrinter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprinter);
        /*
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AddPrinterFragment())
                    .commit();
        }
    */
        addPrinter = (Button) findViewById(R.id.addPrinter);
        addPrinter.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                addAPrinter();
            }
        });
    }

    public void addAPrinter(){
        Printer p = new Printer();
        p.name= findViewById(R.id.printerName).toString();
        p.ipAddr = findViewById(R.id.ipAddr).toString();
    }
    protected void onPause(){
        super.onPause();
    }
/*
    public static class AddPrinterFragment extends PreferenceFragment {

        public AddPrinterFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.printers);

        }
    }
    */
}
