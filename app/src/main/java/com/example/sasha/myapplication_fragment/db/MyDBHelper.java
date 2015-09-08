package com.example.sasha.myapplication_fragment.db;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sasha on 28.08.15.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_contacts";
    private static final int DATABASE_VERSION = 1;

    public static final String CONTACTS_TABLE_NAME = "contacts";

    public static final String CONTACT_NAME = "contact_name";
    public static final String CONTACT_NUMBER = "contact_number";
    public static final String CONTACT_ID = "contact_id";
    private static final String CONTACTS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + CONTACTS_TABLE_NAME + " (" + CONTACT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CONTACT_NAME + " TEXT, " +
                    CONTACT_NUMBER + " TEXT);";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONTACTS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i1, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        onCreate(db);
    }
}
