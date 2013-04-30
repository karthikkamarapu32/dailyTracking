package com.AndroidProject.dailyTracking;

import com.example.dailytracking.R;
import com.example.dailytracking.R.layout;
import com.example.dailytracking.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TransactionHistoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_history);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_history, menu);
		return true;
	}

}
