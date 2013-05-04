package com.AndroidProject.dailyTracking;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.example.dailytracking.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/* The activity displays Track History page */
public class TrackHistoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track_history);

		/* Action performed on click of locHistory Button */
		Button locHistoryButton = (Button)findViewById(R.id.Location);
		locHistoryButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i = new Intent(TrackHistoryActivity.this, LocationHistoryActivity.class);
				startActivity(i);
			}
		});
		
		/* Action performed on click of Transaction History Button */
		Button transHistoryButton = (Button)findViewById(R.id.Money);
		transHistoryButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i = new Intent(TrackHistoryActivity.this, TransactionHistoryActivity.class);
				startActivity(i);
			}
		});

		/* On click of BACK button go to home page */
		Button backButton = (Button)findViewById(R.id.Back);
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i = new Intent(TrackHistoryActivity.this,HomePageActivity.class);
				startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.track_history, menu);
		return true;
	}
	
	public void clearLocationHistory(View view) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setTitle("Clear History");
		adb.setMessage("Are you sure you want to delete all of your location history?");
		adb.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				DataBaseHandler dbh = new DataBaseHandler(TrackHistoryActivity.this);
				dbh.clearLocationHistory();
		        Toast.makeText(TrackHistoryActivity.this, "Location History Deleted", Toast.LENGTH_SHORT).show();
		    }});
		adb.setNegativeButton(android.R.string.no, null).show();
	}
	
	public void clearSpendingHistory(View view) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setTitle("Clear History");
		adb.setMessage("Are you sure you want to delete all of your spending history?");
		adb.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				DataBaseHandler dbh = new DataBaseHandler(TrackHistoryActivity.this);
				dbh.clearTransactionHistory();
		        Toast.makeText(TrackHistoryActivity.this, "Spending History Deleted", Toast.LENGTH_SHORT).show();
		    }});
		adb.setNegativeButton(android.R.string.no, null).show();
	}

}
