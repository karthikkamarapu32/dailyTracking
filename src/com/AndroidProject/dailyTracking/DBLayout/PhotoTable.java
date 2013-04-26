package com.AndroidProject.dailyTracking.DBLayout;

import java.sql.Time;
import java.util.List;
import com.AndroidProject.dailyTracking.entities.Photo;

public class PhotoTable {

	/**
	 * Creates the photo table.
	 */
	public static void create() {
		
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
	public static List<Photo> read(Time start, Time end) {
		return null;
	}
	
	/**
	 * Stores a new photo in the database.
	 * 
	 * @param uri the URI of the photo.
	 * @param comment the comment associated with the photo.
	 * @param time the time of the photo.
	 */
	public static void write(String uri, String comment, Time time) {
		
	}
	
	/**
	 * Stores a new photo in the database with an associated location.
	 * 
	 * @param uri the URI of the photo.
	 * @param comment the comment associated with the photo.
	 * @param time the time of the photo.
	 * @param latitude the latitude of the user's location.
	 * @param longitude the longitude of the user's location.
	 */
	public static void write(String uri, String comment, Time time, int latitude, int longitude) {
		
	}
	
	/**
	 * Deletes all photos from the database that took place in the
	 * specified time interval. If start and/or end are null, then that side
	 * of the time interval is unbounded.
	 * 
	 * @param start the start time.
	 * @param end the end time.
	 */
	public static void delete(Time start, Time end) {
		
	}
	
}
