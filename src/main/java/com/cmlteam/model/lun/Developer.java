package com.cmlteam.model.lun;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 5/27/17 9:15 PM
 */


@XmlRootElement
public class Developer {
	@XmlElement
	public long companyId;
	@XmlElement
	public String name;
	@XmlElement
	public String site;
	@XmlElement(name = "redirect_site")
	public String redirectUrl;
	@XmlElement
	public long rank;
	@XmlElement
	public String companyUrl;
}
