package com.AndroidProject.dailyTracking;

import java.util.List;
import java.util.Map;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.AndroidProject.dailyTracking.entities.Location;
import com.AndroidProject.dailyTracking.entities.Transaction;
import com.AndroidProject.dailyTracking.entities.TransactionLogic;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TransactionHistoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_history);
		
		/* Initialize DataBase Handler and call getTransactions()
		 * which gets all the transactions */
		DataBaseHandler dbh = new DataBaseHandler(this);
		List<Transaction> transactions = dbh.getTransactions();
		/* Close the db */
		dbh.close();
		
		List<String> arrayOfNames = TransactionLogic.getListOfNames(transactions);
		
		/* List View which displays all the locations */
		ListView listView = (ListView) findViewById(R.id.listView1);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayOfNames);
		
		listView.setAdapter(adapter);
		Map<Integer, Location> locInd = TransactionLogic.getLocationsIndices(transactions);
		listView.setOnItemClickListener(new TransClickListener(locInd));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_history, menu);
		return true;
	}
	
	private class TransClickListener implements OnItemClickListener {

	    Map<Integer, Location> locInd;

	    public TransClickListener(Map<Integer, Location> locInd) {
	       this.locInd = locInd;
	    }

		@Override
		public void onItemClick(AdapterView<?> parentView, View childView,
				int position, long id) {
			
			Location loc = locInd.get(position);
			if (loc != null) {
				Intent intent = new Intent(TransactionHistoryActivity.this, TransHistoryMapActivity.class);
				intent.putExtra("com.AndroidProject.dailyTracking.LATITUDE", loc.getLat());
				intent.putExtra("com.AndroidProject.dailyTracking.LONGITUDE", loc.getLon());
				startActivity(intent);
			}
			
		}

	 }

}
