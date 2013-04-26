package com.AndroidProject.dailyTracking.entities;

import java.sql.Time;

public class Transaction {
	
	private double amount;
	private String comment;
	private Time time;
	private int latitude;
	private int longitude;
	
	public Transaction(double amount, String comment, Time time, int latitude, int longitude) {
		this.amount = amount;
		this.comment = comment;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public Time getTime() {
		return this.time;
	}
	
	public int getLatitude() {
		return this.latitude;
	}
	
	public int getLongitude() {
		return this.longitude;
	}
	
}
