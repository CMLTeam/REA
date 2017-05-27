package com.cmlteam.model.lun;

import openchat.api.messenger.json.AbstractJson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 5/27/17 6:48 PM
 */


@XmlRootElement
public class Img extends AbstractJson {
	@XmlElement
	public String small;

	@XmlElement
	public String big;
	@XmlElement(name = "main_thumb")
	public String mainThumb;

}
