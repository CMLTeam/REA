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

	public static String resolveAddress(String address) {
		try {
			GeocodingResult[] results = GeocodingApi.geocode(CTX, address).await();
			System.out.println(results);
			return results[0].toString();
		}
		catch(ApiException e) {
			e.printStackTrace();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		resolveAddress("ЖК Голосеевский");
	}

}
