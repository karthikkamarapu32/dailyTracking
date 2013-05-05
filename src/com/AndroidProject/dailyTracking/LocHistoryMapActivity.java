package com.AndroidProject.dailyTracking;

import java.util.List;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.AndroidProject.dailyTracking.entities.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class LocHistoryMapActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loc_history_map);
		
		GoogleMap mMap;
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		DataBaseHandler dbh = new DataBaseHandler(this);
		List<Location> locations = dbh.getAllLocations();
		dbh.close();
		for (Location loc : locations) {
			mMap.addMarker(new MarkerOptions()
	        .position(new LatLng(loc.getLat(), loc.getLon()))
	        .title(loc.getTime()));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loc_history_map, menu);
		return true;
	}

}
