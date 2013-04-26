package com.AndroidProject.dailyTracking.entities;

import java.sql.Time;
import java.util.List;

import com.AndroidProject.dailyTracking.DBLayout.TransactionTable;

public class TransactionLogic {
	
	
	/**
	 * Add a transaction to the user history.
	 * 
	 * @param amount the amount of the transaction.
	 * @param comment optional comment.
	 * @param location boolean to determine if location is also stored.
	 */
	public static void addTransaction(double amount, String comment, boolean location) {
		
		if (location) {
			// get current latitude
			int latitude = 0;
			// get current longitude
			int longitude = 0;
			// get current time;
			Time time = new Time(0);
			TransactionTable.write(amount, comment, time, latitude, longitude);
		} else {
			// get current time;
			Time time = new Time(0);
			TransactionTable.write(amount, comment, time);
		}

	}
	
	/**
	 * Gets an ordered list of user transactions that took place during the
	 * specified time interval. If start and/or end are null, then that side
	 * of the time interval is unbounded.
	 * 
	 * @param start the start time.
	 * @param end the end time.
	 * @return the list of transactions.
	 */
	public static List<Transaction> getLocationHistory(Time start, Time end) {
		return TransactionTable.read(start, end);
	}
	
}
