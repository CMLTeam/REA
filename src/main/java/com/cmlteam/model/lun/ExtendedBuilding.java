package com.cmlteam.model.lun;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 5/27/17 10:15 PM
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ExtendedBuilding extends Building {
	@XmlElement
	public String type;
	@XmlElement
	public State state;
	@XmlElement
	public BuildingClass buildingClass;
	@XmlElement
	public Developer developers;
}
