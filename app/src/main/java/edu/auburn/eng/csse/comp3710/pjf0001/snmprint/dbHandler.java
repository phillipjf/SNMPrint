package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class dbHandler extends SQLiteOpenHelper{
    private static final String DEBUG_TAG = "db_Print";
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "printerManager";

    // Contacts table name
    private static final String TABLE_PRINTERS = "printers";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IP = "ip";

    public dbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRINTERS_TABLE = "CREATE TABLE " + TABLE_PRINTERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_IP + " TEXT" + ")";
        db.execSQL(CREATE_PRINTERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRINTERS);

        // Create tables again
        onCreate(db);
    }

    // Adding new printer
    public void addPrinter(Printer p) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, p.mName); // Printer Name
        values.put(KEY_IP, p.mIpAddr); // Printer IP

        // Inserting Row
        db.insert(TABLE_PRINTERS, null, values);
        Log.i(DEBUG_TAG, "Item Added!");
        db.close(); // Closing database connection
    }

    // Getting single printer
    public Printer getPrinter(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRINTERS, new String[] { KEY_ID, KEY_NAME, KEY_IP }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Printer p = new Printer();
        p.setID(cursor.getLong(0));
        p.setPrinterName(cursor.getString(1));
        p.setIP(cursor.getString(2));
        // return contact
        return p;
    }

    // Getting All Printers
    public ArrayList<Printer> getAllPrinters() {
        ArrayList<Printer> printerList = new ArrayList<Printer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRINTERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Printer p = new Printer();
                p.setID(cursor.getLong(0));
                p.setPrinterName(cursor.getString(1));
                p.setIP(cursor.getString(2));

                // Adding printer to list
                printerList.add(p);
            } while (cursor.moveToNext());
        }
        // return contact list
        return printerList;
    }

    // Updating single printer
    public int updatePrinter(Printer p) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, p.mName);
        values.put(KEY_IP, p.mIpAddr);

        // updating row
        return db.update(TABLE_PRINTERS, values, KEY_ID + " = ?", new String[] {String.valueOf(p.mPrinterID)});
    }

    // Deleting single printer
    public void deletePrinter(Printer p) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRINTERS, KEY_ID + " = ?", new String[] { String.valueOf(p.mPrinterID) });
        db.close();
    }

    // Deleting single printer
    public void deletePrinterbyID(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRINTERS, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }
}
