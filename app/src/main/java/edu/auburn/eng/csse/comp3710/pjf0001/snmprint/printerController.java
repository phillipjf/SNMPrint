package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class printerController extends ActionBarActivity {
    Button addPrinter;
    final dbHandler db = new dbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprinter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);



        final EditText nameText = (EditText)findViewById(R.id.printerName);
        final EditText ipText = (EditText)findViewById(R.id.ipAddr);
        final ListView list = (ListView)findViewById(R.id.printerList);
        updateList(list);

        addPrinter = (Button) findViewById(R.id.addPrinter);
        addPrinter.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String pName = nameText.getText().toString();
                String pIP = ipText.getText().toString();
                Printer p = new Printer(pName, pIP);
                db.addContact(p);
                updateList(list);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db.deletePrinterbyID(id);
                editItemAlert(id);
                updateList(list);
            }
        });
    }
    void editItemAlert(final long id){
        AlertDialog.Builder cd = new AlertDialog.Builder(printerController.this);
        cd.setTitle("Edit Printer");

        LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.edit_item_alert,null);

        Button btn = (Button)view.findViewById(R.id.idButton);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                //Delete the item from db
                db.deletePrinterbyID(id);
                Toast.makeText(getApplicationContext(), "Delete This Printer", Toast.LENGTH_LONG).show();
            }});

        cd.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // Update the printer item based off input

            }});

        cd.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // Do nothing


            }});

        cd.setView(view);
        cd.show();
    }

    public void updateList(ListView list){
        ArrayAdapter<Printer> mArrayAdapter = new ArrayAdapter<Printer>(this, android.R.layout.simple_list_item_1, android.R.id.text1, db.getAllPrinters());
        list.setAdapter(mArrayAdapter);
    }

    protected void onPause(){
        super.onPause();
    }
}
