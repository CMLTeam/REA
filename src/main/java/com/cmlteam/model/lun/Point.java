package com.cmlteam.model.lun;

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
}
