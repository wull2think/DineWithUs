package cmu.andrew.htay.dinewithus.DBLayout;

/**
 * Created by HuiJun on 4/12/16.
 */

/**
 * Created by HuiJun on 3/24/16.
 */
// DatabaseConnector.java
// Provides easy connection and creation of MortgageCalc database.
// functions based off sample code from 18-641 TestApp3Build2 project

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector
{
    // database name
    protected static final String DATABASE_NAME = "DineWithUsDB";
    protected SQLiteDatabase database; // database object
    protected DatabaseOpenHelper databaseOpenHelper; // database helper

    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    } // end DatabaseConnector constructor

    // open the database connection
    public void open() throws SQLException
    {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close()
    {
        if (database != null)
            database.close(); // close the database connection
    } // end method close

    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the contacts table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // query to create a new table named MortageCalcs
            String createQuery = "CREATE TABLE Profile" +
                    "(_id integer primary key autoincrement," +
                    "user TEXT);";

            db.execSQL(createQuery); // execute the query

            createQuery = "CREATE TABLE Appointments" +
                    "(_id integer primary key autoincrement," +
                    "userA STRING, userB STRING, store int," +
                    "date TEXT);";

            db.execSQL(createQuery); // execute the query

            createQuery = "CREATE TABLE Schedule" +
                    "(_id integer primary key autoincrement," +
                    "date TEXT, startTime INTEGER, endTime INTEGER);";

            db.execSQL(createQuery); // execute the query
            createQuery = "CREATE TABLE Stores" +
                    "(_id integer primary key autoincrement," +
                    "name TEXT, location TEXT, startTime INTEGER," +
                    "endTime INTEGER, description TEXT, menu TEXT, website TEXT);";

            db.execSQL(createQuery); // execute the query


        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        } // end method onUpgrade
    } // end class DatabaseOpenHelper
} // end class DatabaseConnector

