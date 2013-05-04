package com.AndroidProject.dailyTracking.services;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.AndroidProject.dailyTracking.entities.GPSTracker;
import com.AndroidProject.dailyTracking.entities.Location;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/* Create and Select values from Location Table */
public class LocationService extends Service {
	
	public void onCreate() {
		super.onCreate();
	}

	/* OnStartCommand called every time the service is called */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		this.addCurLocation();
		return START_STICKY;
	}

	/* Stop Service to kill the running service */
	@Override
	public boolean stopService(Intent intent) {
		// TODO Auto-generated method stub

		return super.stopService(intent);

	}
	
	/* Insert into data base of tables LOCATION and TIME,
	 * values generated from GPS Tracker.
	 */
	private void addCurLocation() {
		
		// create class object
		GPSTracker gps = new GPSTracker(LocationService.this);
		Toast.makeText(getApplicationContext(), "In addCurLocation", Toast.LENGTH_LONG).show();
		// check if GPS enabled
		if(gps.LocationExists()) {
			/* Get the latitude and longitude */
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
			
			DataBaseHandler dbh = new DataBaseHandler(LocationService.this);
			dbh.addLocation(new Location(0, latitude, longitude, null));
			dbh.close();
		}
		
	}

	@Override
	public IBinder onBind(Intent arg0) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
