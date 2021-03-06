package com.cmlteam.model.lun;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import openchat.api.messenger.json.AbstractJson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 5/27/17 6:49 PM
 */


@XmlRootElement
public class Building extends AbstractJson {
	@XmlElement
	public long id;
	@XmlElement
	public String state;
	@XmlElement
	public String name;
	@XmlElement
	public Img img;
	@XmlElement
	public String synonym;
	@XmlElement
	public long regionId;
	@XmlElement
	public double lat;
	@XmlElement
	public double lng;
	@XmlElement
	public City city;
	@XmlElement
	public String address;
	@XmlElement
	public Point[] polygon;
	@XmlElement
	public String analytics;

	@XmlElement
	public String type;
	@XmlElement
	public BuildingClass buildingClass;
	@XmlElement
	public Developer developers;

	public String formattedAddress;
}
