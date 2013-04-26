package com.AndroidProject.dailyTracking.entities;

import java.sql.Time;
import java.util.List;

import com.AndroidProject.dailyTracking.DBLayout.PhotoTable;

public class PhotoLogic {
	
	/**
	 * Add a photo to the user history.
	 * 
	 * @param uri the URI of the photo.
	 * @param comment optional comment.
	 * @param location boolean to determine if location is also stored.
	 */
	public static void addPhoto(String uri, String comment, boolean location) {
		
		if (location) {
			// get current latitude
			int latitude = 0;
			// get current longitude
			int longitude = 0;
			// get current time;
			Time time = new Time(0);
			PhotoTable.write(uri, comment, time, latitude, longitude);
		} else {
			// get current time;
			Time time = new Time(0);
			PhotoTable.write(uri, comment, time);
		}

	}
	
	/**
	 * Gets an ordered list of user photos that took place during the
	 * specified time interval. If start and/or end are null, then that side
	 * of the time interval is unbounded.
	 * 
	 * @param start the start time.
	 * @param end the end time.
	 * @return the list of photos.
	 */
	public static List<Photo> getLocationHistory(Time start, Time end) {
		return PhotoTable.read(start, end);
	}
	
}
