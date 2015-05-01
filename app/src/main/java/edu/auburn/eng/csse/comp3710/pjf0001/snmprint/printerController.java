package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
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

import java.util.ArrayList;


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
                Printer p = new Printer();
                p.setPrinterName(pName);
                p.setIP(pIP);
                nameText.setText("");
                ipText.setText("");
                db.addPrinter(p);
                updateList(list);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editItemAlert(position);
                updateList(list);
            }
        });
    }
    void editItemAlert(final int position){
        AlertDialog.Builder cd = new AlertDialog.Builder(printerController.this);

        LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.edit_item_alert, null);

        cd.setTitle("Edit Printer");

        final AlertDialog dialog = cd.create();

        final ListView list = (ListView)findViewById(R.id.printerList);
        ArrayList<Printer> pList=  db.getAllPrinters();
        final Printer p = pList.get(position);

        final EditText printerName = (EditText)view.findViewById(R.id.alertPrinterName);
        final EditText printerIP = (EditText)view.findViewById(R.id.alertipAddr);
        printerName.setText(p.getPrinterName());
        printerIP.setText(p.getIP());

        Button btnDelete = (Button)view.findViewById(R.id.deleteButton);
        Button btnOkay = (Button)view.findViewById(R.id.okButton);
        Button btnCancel = (Button)view.findViewById(R.id.cancelButton);

        btnDelete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                //Delete the item from db
                db.deletePrinter(p);
                Toast.makeText(getApplicationContext(), "Delete This Printer: " + p.getPrinterName(), Toast.LENGTH_LONG).show();
                updateList(list);
                dialog.dismiss();
            }
        });

        btnOkay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //update the item
                p.setPrinterName(printerName.getText().toString());
                p.setIP(printerIP.getText().toString());
                updateList(list);
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //do nothing
                dialog.dismiss();
            }
        });

        dialog.setView(view);
        dialog.show();
    }

    public void updateList(ListView list){
        LevelAdapter adp=new LevelAdapter(this, R.layout.list_item, db.getAllPrinters());
        list.setAdapter(adp);
    }

    protected void onPause(){
        super.onPause();
    }
}
