package com.AndroidProject.dailyTracking;

import java.util.ArrayList;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.example.dailytracking.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/* The Activity displays Location history of the App */ 
public class LocationHistoryActivity extends Activity 
{

	/* Declartion of local variables */
	private ListView listView;
	public static ArrayList<String> ArrayofName = new ArrayList<String>();


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_history);

		/* Initialize DataBase Handler and call getAllLocations()
		 * which gets all the locations */
		DataBaseHandler db = new DataBaseHandler(this);
		db.getAllLocations();

		/* List View which displays all the locations */
		listView = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ArrayofName);

		listView.setAdapter(adapter);

		/* Close the db */
		db.close();
	}
}
