package com.AndroidProject.dailyTracking.entities;

public class Location {
	
	private int id;
	private double lat;
	private double lon;
	private String time;
	
	public Location(int id, double lat, double lon, String time) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.time = time;
	}
	
	public int getID() {
		return this.id;
	}
	
	public double getLat() {
		return this.lat;
	}
	
	public double getLon() {
		return this.lon;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String toString() {
		return "LATITUDE\t\t" + this.lat +"\nLONGITUDE\t"+ this.lon + "\nTIMESTAMP\t" + this.time;
	}
}
