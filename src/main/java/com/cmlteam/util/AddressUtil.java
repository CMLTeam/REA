package com.cmlteam.util;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;

/**
 * @author vgorin
 *         file created on 5/27/17 4:20 PM
 */


public class AddressUtil {
	private static final GeoApiContext CTX;

	static {
		String apiKey = PropertyUtil.getProperty("GEO_API_KEY");
		if(apiKey == null) {
			throw new RuntimeException("please set the GEO_API_KEY");
		}
		CTX = new GeoApiContext().setApiKey(apiKey);
	}

	static GeocodingResult geocodeAddress(String address) throws IOException {
		try {
			GeocodingResult[] results = GeocodingApi.geocode(CTX, address).await();
			if(results != null && results.length > 0) {
				return results[0];
			}
			return null;
		}
		catch(ApiException | InterruptedException | IOException e) {
			throw new IOException(e);
		}
	}

	public static String resolveAddress(String address) throws IOException {
		GeocodingResult result = geocodeAddress(address);
		return result == null? null: result.formattedAddress;
	}

}
