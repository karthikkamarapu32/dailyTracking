package com.AndroidProject.dailyTracking.entities;

public class Location {
	
	private double lat;
	private double lon;
	private String time;
	
	public Location(double lat, double lon, String time) {
		this.lat = lat;
		this.lon = lon;
		this.time = time;
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
		return null;
	}
}
