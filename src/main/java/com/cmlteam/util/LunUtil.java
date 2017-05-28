package com.cmlteam.util;

import com.cmlteam.model.lun.Building;
import com.cmlteam.model.lun.Developer;
import com.cmlteam.model.lun.LunModel;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author vgorin
 *         file created on 5/27/17 8:40 PM
 */


public class LunUtil {
	private static final LunModel MODEL;
	private static final Pattern RANK_PATTERN = Pattern.compile("\"rank\": (\\d+),");

	private static final Logger log = LoggerFactory.getLogger(LunUtil.class);

	public static final long RATING_NORMALIZER = 10;

	static {
		try {
			String shortJson = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("lundb_1.json").toURI())));
			MODEL = JsonUtil.parseJson(shortJson, LunModel.class);

			long maxRank = 0;

			for(int i = 0; i < MODEL.buildings.length; i++) {
				long id = MODEL.buildings[i].id;
				MODEL.buildings[i].developers = new Developer();
				String extendedJson = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(String.format("lundb/building_%d.json", id)).toURI())));
				Matcher m = RANK_PATTERN.matcher(extendedJson);
				if(m.find() && m.groupCount() > 0) {
					try {
						MODEL.buildings[i].developers.rank = Long.parseLong(m.group(1)) + 1;
					}
					catch(Exception e) {
						log.warn("", e);
						MODEL.buildings[i].developers.rank = -1;
					}
					if(MODEL.buildings[i].developers.rank > maxRank) {
						maxRank = MODEL.buildings[i].developers.rank;
					}
				}
			}

			// normalize
			for(int i = 0; i < MODEL.buildings.length; i++) {
				if(MODEL.buildings[i].developers.rank < 0) {
					MODEL.buildings[i].developers.rank = (long) (Math.random() * maxRank);
				}
				else {
					MODEL.buildings[i].developers.rank = RATING_NORMALIZER - RATING_NORMALIZER * MODEL.buildings[i].developers.rank / maxRank;
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Building getClosestBuilding(String address) throws IOException {
		GeocodingResult result = AddressUtil.geocodeAddress(address);
		if(result != null) {
			LatLng latLng = result.geometry.location;
			Building building = GeoUtil.getClosestBuilding(MODEL, latLng);
			building.formattedAddress = result.formattedAddress;
			return building;
		}
		return null;
	}
}
