package com.AndroidProject.dailyTracking;

import com.example.dailytracking.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class HomePageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		
		
		Button saveButton = (Button)findViewById(R.id.saveValue);
		saveButton.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View arg0) {
	         Intent i = new Intent(HomePageActivity.this, TrackOptionsActivity.class);
	         startActivity(i);
	         }
	      });
		
		Button EnterBillButton = (Button)findViewById(R.id.enterBillValue);
		EnterBillButton.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View arg0) {
	         Intent i = new Intent(HomePageActivity.this, EnterBillsActivity.class);
	         startActivity(i);
	         }
	      });
		
		Button trackHistoryButton = (Button)findViewById(R.id.history);
		trackHistoryButton.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View arg0) {
	         Intent i = new Intent(HomePageActivity.this, TrackHistoryActivity.class);
	         startActivity(i);
	         }
	      });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
