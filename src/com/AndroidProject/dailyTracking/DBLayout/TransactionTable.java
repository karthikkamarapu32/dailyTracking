package com.AndroidProject.dailyTracking.DBLayout;

import java.sql.Time;
import java.util.List;
import com.AndroidProject.dailyTracking.entities.Transaction;

public class TransactionTable {

	/**
	 * Creates the transaction table.
	 */
	public static void create() {
		
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
	public static List<Transaction> read(Time start, Time end) {
		return null;
	}
	
	/**
	 * Stores a new transaction in the database.
	 * 
	 * @param amount the amount of the transaction.
	 * @param comment the comment associated with the transaction.
	 * @param time the time of the transaction.
	 */
	public static void write(double amount, String comment, Time time) {
		
	}
	
	/**
	 * Stores a new transaction in the database with an associated location.
	 * 
	 * @param amount the amount of the transaction.
	 * @param comment the comment associated with the transaction.
	 * @param time the time of the transaction.
	 * @param latitude the latitude of the user's location.
	 * @param longitude the longitude of the user's location.
	 */
	public static void write(double amount, String comment, Time time, int latitude, int longitude) {
		
	}
	
	/**
	 * Deletes all transactions from the database that took place in the
	 * specified time interval. If start and/or end are null, then that side
	 * of the time interval is unbounded.
	 * 
	 * @param start the start time.
	 * @param end the end time.
	 */
	public static void delete(Time start, Time end) {
		
	}
	
}
