package com.example.neshat.bluedot.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandlerforbeacon extends SQLiteOpenHelper
{
    private static final String TAG = SQLiteHandlerforbeacon.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_BEACON = "beacon";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_MAJOR = "Major";
    private static final String KEY_MINOR = "Minor";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_LINK = "link";

    public SQLiteHandlerforbeacon(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_BEACON_TABLE = "CREATE TABLE " + TABLE_BEACON + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MAJOR + " TEXT,"
                + KEY_MINOR + " TEXT UNIQUE," + KEY_LOCATION + " TEXT,"
                + KEY_LINK + " TEXT" + ");";
        db.execSQL(CREATE_BEACON_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BEACON);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String major, String minor, String location, String link)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MAJOR, major);
        values.put(KEY_MINOR, minor);
        values.put(KEY_LOCATION, location);
        values.put(KEY_LINK, link);

        // Inserting Row
        long id = db.insert(TABLE_BEACON, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New beacon inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getBeaconDetails()
    {
        HashMap<String, String> beacon = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_BEACON;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0)
        {
            beacon.put("major", cursor.getString(cursor.getColumnIndex("major")));
            beacon.put("minor", cursor.getString(cursor.getColumnIndex("minor")));
            beacon.put("location", cursor.getString(cursor.getColumnIndex("location")));
            beacon.put("link", cursor.getString(cursor.getColumnIndex("linktoadd")));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching beacon from Sqlite: " + beacon.toString());

        return beacon;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_BEACON, null, null);
        db.close();

        Log.d(TAG, "Deleted all beacon info from sqlite");
    }
}
