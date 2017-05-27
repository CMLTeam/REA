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
	private static final GeoApiContext CTX = new GeoApiContext().setApiKey(PropertyUtil.loadProperty("GEO_API_KEY"));

	public static String resolveAddress(String address) throws IOException {
		try {
			GeocodingResult[] results = GeocodingApi.geocode(CTX, address).await();
			if(results != null && results.length > 0) {
				return results[0].formattedAddress;
			}
			return null;
		}
		catch(ApiException | InterruptedException | IOException e) {
			throw new IOException(e);
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println(resolveAddress("ЖК Голосеевский"));
		System.out.println(resolveAddress("ЖК Комфорт Таун"));
	}

}
