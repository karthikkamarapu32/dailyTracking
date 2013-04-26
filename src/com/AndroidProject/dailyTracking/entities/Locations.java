package com.AndroidProject.dailyTracking.entities;

public class Locations 
{

	/* Declartion of local Variables */
	int _id;
	String latitude,longitude;
	String timeStamp;

	/* Getter and Setter method for TrackId */
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}

	/* Getter and Setter Method for Latitude */
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/* Getter and Setter Method for Longitude */
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/* Getter and Setter Method for TimeStamp*/
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
