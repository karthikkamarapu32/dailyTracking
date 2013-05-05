package com.AndroidProject.dailyTracking.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionLogic {
	
	public static List<String> getListOfNames(List<Transaction> transactions) {
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
	
	public static Map<Integer, Location> getLocationsIndices(List<Transaction> transactions) {
		Map<Integer, Location> locInd = new HashMap<Integer, Location>();
		// 2 because items 0 and 1 of the list are total spending stats
		int i = 2;
		for (Transaction trans : transactions) {
			Location loc = trans.getLocation();
			if (loc != null) {
				locInd.put(i, loc);
			}
			i++;
		}
		return locInd;
	}
	
	private static double roundTwoDecimals(double x) {
	    return (double) Math.round(x * 100) / 100;
	}
	
}
