package com.cmlteam.util;

import com.cmlteam.model.lun.Building;
import com.cmlteam.model.lun.LunModel;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author vgorin
 *         file created on 5/27/17 8:40 PM
 */


public class LunUtil {
	private static final LunModel MODEL;

	static {
		try {
			String shortJson = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("lundb_1.json").toURI())));
			MODEL = JsonUtil.parseJson(shortJson, LunModel.class);

			/*
			for(int i = 0; i < MODEL.buildings.length; i++) {
				long id = MODEL.buildings[i].id;
				String extendedJson = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(String.format("lundb/building_%d.json", id)).toURI())));
				MODEL.buildings[i] = JsonUtil.parseJson(extendedJson, ExtendedBuilding.class);
			}
			*/
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Building getClosestBuilding(String address) throws IOException {
		GeocodingResult result = AddressUtil.resolveAddress(address);
		if(result != null) {
			LatLng latLng = result.geometry.location;
			return GeoUtil.getClosestBuilding(MODEL, latLng);
		}
		return null;
	}
}
