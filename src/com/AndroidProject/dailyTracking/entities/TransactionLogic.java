package com.AndroidProject.dailyTracking.entities;

import java.util.ArrayList;
import java.util.List;

public class TransactionLogic {
	
	public static List<String> getList(List<Transaction> transactions) {
		double totalSpending = 0.0;
		for (Transaction trans : transactions) {
			totalSpending += trans.getAmount();
		}
		double avgSpending = totalSpending / (double) transactions.size();
		List<String> spendingList = new ArrayList<String>();
		spendingList.add("Total Spending: " + roundTwoDecimals(totalSpending));
		spendingList.add("Average Spending: " + roundTwoDecimals(avgSpending));
		for (Transaction trans : transactions) {
			spendingList.add(trans.toString());
		}
		return spendingList;
	}
	
	private static double roundTwoDecimals(double x) {
	    return (double) Math.round(x * 100) / 100;
	}
	
}
