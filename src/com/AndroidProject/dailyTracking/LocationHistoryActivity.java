package com.AndroidProject.dailyTracking;

import java.util.List;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.AndroidProject.dailyTracking.entities.Location;
import com.AndroidProject.dailyTracking.entities.LocationLogic;
import com.example.dailytracking.R;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/* The Activity displays Location history of the App */ 
public class LocationHistoryActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_history);

		/* Initialize DataBase Handler and call getAllLocations()
		 * which gets all the locations */
		DataBaseHandler db = new DataBaseHandler(this);
		List<Location> locations = db.getAllLocations();
		List<String> arrayOfNames = LocationLogic.getList(locations);

		/* List View which displays all the locations */
		ListView listView = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayOfNames);

		listView.setAdapter(adapter);

		/* Close the db */
		db.close();
	}
}
