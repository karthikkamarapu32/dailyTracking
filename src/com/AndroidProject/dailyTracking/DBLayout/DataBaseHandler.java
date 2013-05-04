package com.AndroidProject.dailyTracking.DBLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import com.AndroidProject.dailyTracking.LocationHistoryActivity;
import com.AndroidProject.dailyTracking.entities.Location;
import com.AndroidProject.dailyTracking.entities.Transaction;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/* DataBaseHandler to retrieve values from DB */
public class DataBaseHandler extends SQLiteOpenHelper {

	/* Declare all local Variables */
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DailyTracking2.db";
	private static final String LOC_TABLE_NAME = "LOCATION";
	private static final String TIME_TABLE_NAME = "TIME";
	private static final String TRANSACTION_TABLE_NAME = "BILLS";
	private final DateFormat timestampFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private Context c;
	
	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.c = context;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String locQuery = "CREATE TABLE IF NOT EXISTS " + LOC_TABLE_NAME + " (TRACK_ID INT,LAT DOUBLE, LON DOUBLE );";
		String timeQuery = "CREATE TABLE IF NOT EXISTS " + TIME_TABLE_NAME + " (TRACK_ID INT,TIME_STAMP VARCHAR );";
		String transQuery = "CREATE TABLE IF NOT EXISTS " + TRANSACTION_TABLE_NAME + 
				" (TRACK_ID INT, AMOUNT DOUBLE, STORE VARCHAR, CATEGORY VARCHAR);";
		db.execSQL(locQuery);
		db.execSQL(timeQuery);
		db.execSQL(transQuery);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + LOC_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TIME_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TRANSACTION_TABLE_NAME);

		// Create tables again
		onCreate(db);
	}
	
	public void addTransaction(Transaction transaction) {
		
		int curID = this.getCurID();
		Toast.makeText(this.c, "Current ID " + curID, Toast.LENGTH_SHORT).show();
		/* Insert Query for TRANSACTION Table */		        
		String insertTransQuery = "INSERT INTO " + TRANSACTION_TABLE_NAME +
				" (TRACK_ID,AMOUNT,STORE,CATEGORY) VALUES (" + "'" +
				curID + "','" +
				transaction.getAmount() + "','" +
				transaction.getStore() + "','" + 
				transaction.getCategory() + "');";
		
		/* Insert Query for TIME Table */
		String insertTimeQuery = "INSERT INTO " + TIME_TABLE_NAME +
				" (TRACK_ID,TIME_STAMP) VALUES " +
				"( " + "'" + curID + "','" +
				this.getCurTime() + "');";
		
		String insertLocQuery = null;
		Location location = transaction.getLocation();
		if (location != null) {
			/* Insert Query for LOCATION Table */	        
			insertLocQuery = "INSERT INTO " + LOC_TABLE_NAME +
					" (TRACK_ID,LAT,LON) VALUES (" + "'" +
					curID + "','" +
					location.getLat() + "','" +
					location.getLon() + "');";
		}
		
		SQLiteDatabase db = this.getWritableDatabase();
		/* Execute the Queries */
		db.execSQL(insertTransQuery);
		db.execSQL(insertTimeQuery);
		if (location != null) {
			db.execSQL(insertLocQuery);
		}
		
	}
	
	public List<Transaction> getTransactions() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		// Select All Query
		String selectQuery = "SELECT * FROM BILLS,TIME WHERE BILLS.TRACK_ID = TIME.TRACK_ID";

		/* Run the Select query and get it to Cursor */
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to array
		if (cursor.moveToFirst()) {
			// Loop through all the locations 
			do {
				int id = cursor.getInt(cursor.getColumnIndex("TRACK_ID"));
				double amount = cursor.getDouble(cursor.getColumnIndex("AMOUNT"));
				String store = cursor.getString(cursor.getColumnIndex("STORE"));
				String category = cursor.getString(cursor.getColumnIndex("CATEGORY"));
				String time = cursor.getString(cursor.getColumnIndex("TIME_STAMP"));
				transactions.add(new Transaction(id, amount, store, category, null, time));
			} while (cursor.moveToNext());
		}
		
		selectQuery = "SELECT * FROM BILLS,LOCATION WHERE BILLS.TRACK_ID = LOCATION.TRACK_ID";
		cursor = db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(cursor.getColumnIndex("TRACK_ID"));
				double lat = cursor.getDouble(cursor.getColumnIndex("LAT"));
				double lon = cursor.getDouble(cursor.getColumnIndex("LON"));
				for (Transaction trans : transactions) {
					if (trans.getID() == id) {
						trans.setLocation(new Location(id, lat, lon, trans.getTime()));
					}
				}
			} while (cursor.moveToNext());
		}
		
		return transactions;
		
	}
	
	public void clearTransactionHistory() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TRANSACTION_TABLE_NAME);
		String transQuery = "CREATE TABLE IF NOT EXISTS " + TRANSACTION_TABLE_NAME + 
				" (TRACK_ID VARCHAR, AMOUNT DOUBLE, STORE VARCHAR, CATEGORY VARCHAR);";
		db.execSQL(transQuery);
	}
	
	public void addLocation(Location location) {
		
		int curID = this.getCurID();
		
		/* Insert Query for LOCATION Table */		        
		String insertLocQuery = "INSERT INTO " + LOC_TABLE_NAME +
				" (TRACK_ID,LAT,LON) VALUES (" + "'" +
				curID + "','" +
				location.getLat() + "','" +
				location.getLon() + "');";

		/* Insert Query for TIME Table */
		String insertTimeQuery = "INSERT INTO " + TIME_TABLE_NAME +
				" (TRACK_ID,TIME_STAMP) VALUES " +
				"( " + "'" + curID + "','" +
				this.getCurTime() + "');";
		
		SQLiteDatabase db = this.getWritableDatabase();
		/* Execute the Queries */
		db.execSQL(insertLocQuery);
		db.execSQL(insertTimeQuery);
	}

	// Getting All Locations
	public List<Location> getAllLocations() {
		
		List<Location> locations = new ArrayList<Location>();
		// Select All Query
		String selectQuery = "SELECT * FROM LOCATION,TIME WHERE LOCATION.TRACK_ID = TIME.TRACK_ID";

		/* Run the Select query and get it to Cursor */
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to array
		if (cursor.moveToFirst()) {
			// Loop through all the locations 
			do {
				int id = cursor.getInt(cursor.getColumnIndex("TRACK_ID"));
				double lat = cursor.getDouble(cursor.getColumnIndex("LAT"));
				double lon = cursor.getDouble(cursor.getColumnIndex("LON"));
				String time = cursor.getString(cursor.getColumnIndex("TIME_STAMP"));
				locations.add(new Location(id, lat, lon, time));
			} while (cursor.moveToNext());
		}
		return locations;
		
	}
	
	public void clearLocationHistory() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + LOC_TABLE_NAME);
		String locQuery = "CREATE TABLE IF NOT EXISTS " + LOC_TABLE_NAME + " (TRACK_ID INT,LAT DOUBLE, LON DOUBLE );";
		db.execSQL(locQuery);
	}
	
	private String getCurTime() {
		
		/* Get the time Component */	
		GregorianCalendar greg = new GregorianCalendar();
		TimeZone tz = greg.getTimeZone();
		int offset = tz.getOffset(System.currentTimeMillis());
		greg.add(Calendar.SECOND, (offset / 1000) * -1);
		return timestampFormat.format(greg.getTime());
		
	}
	
	private int getCurID() {
		
		/* Select Query to get maximum TrackId from TIME Table.
		 * This is also the recent one.
		 */
		String selQuery = "SELECT MAX(TRACK_ID) FROM " + TIME_TABLE_NAME ;
		/* Run the Select query and get it to Cursor */
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selQuery, null);
		if(cursor.moveToFirst()) {
			return cursor.getInt(0) + 1;
		} else {
			return 1;
		}
		
	}
}