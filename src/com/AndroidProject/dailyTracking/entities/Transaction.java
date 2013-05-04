package com.AndroidProject.dailyTracking.entities;

public class Transaction {
	
	private double amount;
	private String store;
	private String category;
	private String time;
	
	public Transaction(double amount, String store, String category, String time) {
		this.amount = amount;
		this.store = store;
		this.category = category;
		this.time = time;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public String getStore() {
		return this.store;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String toString() {
		return "$" + this.amount + " at " + this.store + " on " + this.time + " for " + this.category;
	}
	
}
