package com.AndroidProject.dailyTracking;

import java.util.List;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.AndroidProject.dailyTracking.entities.Transaction;
import com.AndroidProject.dailyTracking.entities.TransactionLogic;
import com.example.dailytracking.R;
import com.example.dailytracking.R.layout;
import com.example.dailytracking.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TransactionHistoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_history);
		
		/* Initialize DataBase Handler and call getAllLocations()
		 * which gets all the locations */
		DataBaseHandler db = new DataBaseHandler(this);
		List<Transaction> transactions = db.getTransactions();
		List<String> arrayOfNames = TransactionLogic.getList(transactions);

		/* List View which displays all the locations */
		ListView listView = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayOfNames);

		listView.setAdapter(adapter);

		/* Close the db */
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_history, menu);
		return true;
	}

}
