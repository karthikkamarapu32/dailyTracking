package com.AndroidProject.dailyTracking.DBLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.AndroidProject.dailyTracking.entities.GPSTracker;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/* Create and Select values from Location Table */
public class LocationTable extends Service 
{

	/* Declaration of local variables */
	GPSTracker gps;
	public static final String DATABASE = "DailyTracking2.db";
	public static final String LOC_TABLE_NAME = "LOCATION";
	public static final String TIME_TABLE_NAME = "TIME";
	private final DateFormat timestampFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private SQLiteDatabase db;
	private static final String tag = "TrackingService";
	private int trackId;

	public void onCreate() {
		super.onCreate();
		CreateDatabase();
	}

	/* OnStartCommand called every time the service is called */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{
		insertDataBase();
		return START_STICKY;
	}

	/* Stop Service to kill the running service */
	@Override
	public boolean stopService(Intent intent) {
		// TODO Auto-generated method stub

		return super.stopService(intent);

	}

	/* Create DataBase ie LOCATION and TIME Table
	 * if not yet created.
	 */
	private void CreateDatabase() 
	{
		String query = "CREATE TABLE IF NOT EXISTS " + LOC_TABLE_NAME + " (TRACK_ID VARCHAR,LAT VARCHAR, LON VARCHAR );";
		String query1 = "CREATE TABLE IF NOT EXISTS " + TIME_TABLE_NAME + " (TRACK_ID VARCHAR,TIME_STAMP VARCHAR );";
		db = this.openOrCreateDatabase(DATABASE, SQLiteDatabase.OPEN_READWRITE, null);

		db.execSQL(query);
		db.execSQL(query1);
		db.close();
		Log.d(tag, "Database opened ok");
	}

	/* Insert into data base of tables LOCATION and TIME,
	 * values generated from GPS Tracker.
	 */
	private void insertDataBase()
	{
		/* Declartion of local variables */
		String selQuery,maxTrackId;
		String insertLocQuery,insertTimeQuery;

		// create class object
		gps = new GPSTracker(LocationTable.this);

		// check if GPS enabled
		if(gps.LocationExists())
		{

			/* Get the latitude and longitude */
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

			/* Get the time Component */	
			GregorianCalendar greg = new GregorianCalendar();
			TimeZone tz = greg.getTimeZone();
			int offset = tz.getOffset(System.currentTimeMillis());
			greg.add(Calendar.SECOND, (offset / 1000) * -1);


			/* Select Query to get maximum TrackId from TIME Table.
			 * This is also the recent one.
			 */
			selQuery = "SELECT MAX(TRACK_ID) FROM " + TIME_TABLE_NAME ;

			db = openOrCreateDatabase(DATABASE, SQLiteDatabase.OPEN_READWRITE, null);
			if (db.isOpen()) 
			{
				Log.d(tag, "DB is open!");
				
				/* Cursor is run to get the record here*/
				Cursor cursor = db.rawQuery(selQuery, null);
				if(cursor.moveToFirst())
				{
					maxTrackId = cursor.getString(0);

					/* The primary key which is maxTrackId 
					 * is generated here.
					 */
					if(maxTrackId == null)
						trackId = 1;
					else
						trackId = Integer.parseInt(maxTrackId)+1;
				}

				/* Insert Query for LOCATION Table */		        
				insertLocQuery = "INSERT INTO " + LOC_TABLE_NAME +
						" (TRACK_ID,LAT,LON) VALUES (" + "'" +
						String.valueOf(trackId) + "','" +
						gps.getLatitude() + "','" +
						gps.getLongitude() + "');";

				/* Insert Query for TIME Table */
				insertTimeQuery = "INSERT INTO " + TIME_TABLE_NAME +
						" (TRACK_ID,TIME_STAMP) VALUES " +
						"( " + "'" + String.valueOf(trackId) + "','" +
						timestampFormat.format(greg.getTime()) + "');";

				Log.d(tag, insertLocQuery);
				Log.d(tag,insertTimeQuery);

				/* Execute the Queries */
				db.execSQL(insertLocQuery);
				db.execSQL(insertTimeQuery);

			}
			db.close();
		}
	}

	@Override
	public IBinder onBind(Intent arg0) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
