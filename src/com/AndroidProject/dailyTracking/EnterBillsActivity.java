package com.AndroidProject.dailyTracking;

import com.AndroidProject.dailyTracking.DBLayout.DataBaseHandler;
import com.AndroidProject.dailyTracking.entities.Transaction;
import com.example.dailytracking.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
    	
    	DataBaseHandler dbHandler = new DataBaseHandler(this);
    	dbHandler.addTransaction(new Transaction(amount, store, category, null));
    	dbHandler.close();
    	
	}

}
