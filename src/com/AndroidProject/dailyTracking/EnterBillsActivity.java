package com.AndroidProject.dailyTracking;

import com.example.dailytracking.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class EnterBillsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_bills);
		
		
		Button SaveButton= (Button)findViewById(R.id.saveValue);
		SaveButton.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View arg0) {
	         Intent i = new Intent(EnterBillsActivity.this, HomePageActivity.class);
	         startActivity(i);
	         }
	      });
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_bills, menu);
		return true;
	}

}
