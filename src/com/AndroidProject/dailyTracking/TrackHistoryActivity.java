package com.AndroidProject.dailyTracking;

import com.example.dailytracking.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

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

}
