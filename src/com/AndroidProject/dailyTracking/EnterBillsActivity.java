package com.AndroidProject.dailyTracking;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.AndroidProject.dailyTracking.entities.GPSTracker;
import com.AndroidProject.dailyTracking.entities.Location;
import com.AndroidProject.dailyTracking.entities.Transaction;
import com.example.dailytracking.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterBillsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_bills);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_bills, menu);
		return true;
	}
	
	public void onSubmit(View view) {
		
		// get the entered store
		EditText editText = (EditText) findViewById(R.id.storeNameValue);
    	String store = editText.getText().toString();
    	
    	// get the selected category
    	Spinner spinner = (Spinner) findViewById(R.id.categorySpin);
    	String category = String.valueOf(spinner.getSelectedItem());
    	
    	editText = (EditText) findViewById(R.id.billAmountValue);
    	double amount = Double.parseDouble(editText.getText().toString());
    	
    	Location location = null;
    	if (TrackOptionsActivity.moneyEnabled) {
    		GPSTracker gps = new GPSTracker(EnterBillsActivity.this);
    		if (gps.LocationExists()) {
    			location = new Location(0, gps.getLatitude(), gps.getLongitude(), null);
    		}
    	}
    	
    	DataBaseHandler dbh = new DataBaseHandler(this);
    	dbh.addTransaction(new Transaction(0, amount, store, category, location, null));
    	dbh.close();
    	
	}

}
