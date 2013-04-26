package com.AndroidProject.dailyTracking.entities;

import java.sql.Time;

public class Photo {
	
	private String uri;
	private String comment;
	private Time time;
	private int latitude;
	private int longitude;
	
	public Photo(String uri, String comment, Time time, int latitude, int longitude) {
		this.uri = uri;
		this.comment = comment;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getUri() {
		return this.uri;
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
