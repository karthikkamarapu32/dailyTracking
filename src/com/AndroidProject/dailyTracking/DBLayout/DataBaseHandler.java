package com.AndroidProject.dailyTracking.DBLayout;

import java.util.ArrayList;
import java.util.List;

import com.AndroidProject.dailyTracking.LocationHistoryActivity;
import com.AndroidProject.dailyTracking.entities.Locations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* DataBaseHandler to retreive values from DB */
public class DataBaseHandler extends SQLiteOpenHelper {

	/* Declare all local Varaibles */
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DailyTracking2.db";
	private static final String LOC_TABLE_NAME = "LOCATION";
	private static final String TIME_TABLE_NAME = "TIME";


	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + LOC_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TIME_TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	// Getting All Locations
	public void getAllLocations() {
		// Select All Query
		String selectQuery = "SELECT  * FROM LOCATION,TIME WHERE LOCATION.TRACK_ID = TIME.TRACK_ID";

		/* Run the Select query and get it to Cursor */
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to array
		if (cursor.moveToFirst()) 
		{
			// Loop through all the locations 
			do 
			{
				/* Get Locations into LocationHistoryActivity */
				String name =  "LATITUDE\t\t" + cursor.getString(1) +"\nLONGITUDE\t"+ cursor.getString(2) + "\nTIMESTAMP\t" + cursor.getString(4);
				LocationHistoryActivity.ArrayofName.add(name);

			} while (cursor.moveToNext());
		} 
	}
}