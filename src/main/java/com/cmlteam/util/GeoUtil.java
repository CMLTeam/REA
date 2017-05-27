package com.cmlteam.util;

import com.cmlteam.model.lun.Building;
import com.cmlteam.model.lun.LunModel;
import com.google.maps.model.LatLng;

/**
 * @author vgorin
 *         file created on 5/27/17 8:12 PM
 */


class GeoUtil {
	static Building getClosestBuilding(LunModel model, LatLng location) {
		if(model != null && model.buildings != null && model.buildings.length > 0) {
			double lat = location.lat;
			double lng = location.lng;
			Building bestMatch = model.buildings[0];
			double bestDistance = distance(lat, lng, bestMatch.lat, bestMatch.lng);
			double distance;
			for(Building building: model.buildings) {
				distance = distance(lat, lng, building.lat, building.lng);
				if(distance < bestDistance) {
					bestMatch = building;
					bestDistance = distance;
				}
			}
			return bestMatch;
		}
		return null;
	}

	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * <p>
	 * lat1, lng1 Start point lat2, lng2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 *
	 * @return Distance in Meters
	 */
	private static double distance(double lat1, double lng1, double el1, double lat2, double lng2, double el2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lng2 - lng1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}

	private static double distance(double lat1, double lng1, double lat2, double lng2) {
		return distance(lat1, lng1, 0, lat2, lng2, 0);
	}
}
