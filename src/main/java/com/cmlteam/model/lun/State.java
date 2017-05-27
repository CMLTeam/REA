package com.cmlteam.model.lun;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 5/27/17 10:14 PM
 */


@XmlRootElement
public class State {
	@XmlElement
	public String stateType;
	@XmlElement
	public String constant;
	@XmlElement
	public String text;
}
