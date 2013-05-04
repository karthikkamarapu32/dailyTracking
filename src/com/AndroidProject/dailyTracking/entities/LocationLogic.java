package com.AndroidProject.dailyTracking.entities;

import java.util.ArrayList;
import java.util.List;

public class LocationLogic {
	
	public static List<String> getList(List<Location> locations) {
		List<String> stringList = new ArrayList<String>();
		for (Location loc : locations) {
			stringList.add(loc.toString());
		}
		return stringList;
	}
	
}
