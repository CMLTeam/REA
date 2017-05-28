package com.cmlteam.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author vgorin
 *         file created on 5/28/17 11:45 AM
 */


public class MessageUtil {
	/**
	 * Returns one of the strings passed randomly
	 * @param messages input string to choose randomly from
	 * @return one of the strings passed randomly
	 */
	public static String oneOf(String... messages) {
		if(messages.length > 0) {
			return messages[(int) (Math.random() * messages.length)];
		}
		return "";
	}

	public static boolean isGreeting(String text) {
		text = StringUtils.trimToEmpty(text);
		text = StringUtils.lowerCase(text);
		return "hi".equalsIgnoreCase(text)
				|| containsMultiple(text, "привет", "привіт", "здоров", "здравств", "как дела", "hello", "what's up", "whats up");
	}

	static boolean containsMultiple(String text, String... search) {
		if(search.length > 0) {
			for(String s: search) {
				if(StringUtils.contains(text, s)) {
					return true;
				}
			}
		}
		return false;
	}
}
