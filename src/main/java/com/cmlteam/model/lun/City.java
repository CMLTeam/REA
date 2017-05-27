package com.cmlteam.model.lun;

import openchat.api.messenger.json.AbstractJson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 5/27/17 6:47 PM
 */


@XmlRootElement
public class City extends AbstractJson {
	@XmlElement
	public long id;
	@XmlElement
	public String name;
}
