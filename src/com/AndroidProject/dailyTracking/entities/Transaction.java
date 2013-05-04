package com.AndroidProject.dailyTracking.entities;

public class Transaction {
	
	private int id;
	private double amount;
	private String store;
	private String category;
	private Location location;
	private String time;
	
	public Transaction(int id, double amount, String store, String category, Location location, String time) {
		this.id = id;
		this.amount = amount;
		this.store = store;
		this.category = category;
		this.location = location;
		this.time = time;
	}
	
	public int getID() {
		return this.id;
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
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("$" + this.amount + " at " + this.store + " on " + this.time + " for " + this.category);
		if (this.location != null) {
			sb.append("\nLatitude: " + this.location.getLat());
			sb.append("\nLongitude: " + this.location.getLon());
		}
		return sb.toString();
	}
	
}
