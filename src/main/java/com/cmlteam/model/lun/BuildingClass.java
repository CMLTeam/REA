package com.cmlteam.model.lun;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 5/27/17 10:16 PM
 */


@XmlRootElement
public class BuildingClass {
	@XmlElement
	public long id;
	@XmlElement
	public String text;
}
