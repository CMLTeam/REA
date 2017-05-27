package com.cmlteam.model.lun;

import openchat.api.messenger.json.AbstractJson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * @author vgorin
 *         file created on 5/27/17 6:58 PM
 */


@XmlRootElement
public class LunModel extends AbstractJson {
	@XmlElement
	public Map<Long, Building> buildings;
}
