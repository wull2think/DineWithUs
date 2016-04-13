package cmu.andrew.htay.dinewithus.util;

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
    private static final String DATABASE_NAME = "DineWithUsDB";
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper

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

    // inserts a new calculation in the database
    public void storeCalc(int zip, double totalPayment,
                          double monthlyPayment, String payoffDate)
    {
        System.out.println("Writing to DB");
        ContentValues newCalc = new ContentValues();
        newCalc.put("zip", zip);
        newCalc.put("totalPayment", totalPayment);
        newCalc.put("monthlyPayment", monthlyPayment);
        newCalc.put("payoffDate", payoffDate);
        open(); // open the database
        database.insert("MortageCalcs", null, newCalc);
        close(); // close the database
    } // end method insertContact


    public Cursor getCalc(long id)
    {
        return database.query(
                "MortageCalcs", null, "_id=" + id, null, null, null, null);
    } // end method getOnContact

    // delete the calculation specified by the given id
    public void deleteCalc(long id)
    {
        open(); // open the database
        database.delete("MortageCalcs", "_id=" + id, null);
        close(); // close the database
    } // end method deleteContact

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
            String createQuery = "CREATE TABLE MortageCalcs" +
                    "(_id integer primary key autoincrement," +
                    "zip INTEGER, totalPayment DOUBLE, monthlyPayment DOUBLE," +
                    "payoffDate TEXT);";

            db.execSQL(createQuery); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        } // end method onUpgrade
    } // end class DatabaseOpenHelper
} // end class DatabaseConnector

