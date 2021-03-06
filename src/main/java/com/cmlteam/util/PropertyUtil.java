package com.cmlteam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vgorin
 *         file created on 5/27/17 4:32 PM
 */


public class PropertyUtil {
	private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);

	public static String getProperty(String key, String defaultValue) {
		// try to load property as is
		String value = getProperty(key);

		// try to load it lowercase, convert SOME_KEY to some-key
		if(value == null) {
			key = key.toLowerCase().replaceAll("_", "-");
			value = getProperty(key);
		}

		// try to load it uppercase, convert some-key to SOME_KEY
		if(value == null) {
			key = key.toUpperCase().replaceAll("\\-", "_");
			value = getProperty(key);
		}

		// fallback to default value
		return value == null? defaultValue: value;
	}

	public static String getProperty(String key) {
		// try to load property from JAVA_OPTS like -Dname=value
		String value = System.getProperty(key);

		// fallback to system environment variable if value is null
		return value == null? System.getenv(key): value;
	}

}
