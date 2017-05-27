package com.cmlteam.model.lun;

import com.google.maps.model.LatLng;
import openchat.api.messenger.json.AbstractJson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 5/27/17 6:45 PM
 */


@XmlRootElement
public class Point extends AbstractJson {
	@XmlElement
	public double lat;
	@XmlElement
	public double lng;

	public LatLng toLatLng() {
		return new LatLng(lat, lng);
	}

	public static Point fromLatLng(LatLng latLng) {
		Point p = new Point();
		p.lat = latLng.lat;
		p.lng = latLng.lng;
		return p;
	}
}
