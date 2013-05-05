package com.AndroidProject.dailyTracking;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class TransHistoryMapActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trans_history_map);
		
		GoogleMap mMap;
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		Intent intent = getIntent();
		double lat = intent.getDoubleExtra("com.AndroidProject.dailyTracking.LATITUDE", 0.0);
		double lon = intent.getDoubleExtra("com.AndroidProject.dailyTracking.LONGITUDE", 0.0);
		mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trans_history_map, menu);
		return true;
	}

}
