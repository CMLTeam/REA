package com.cmlteam.util;

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
}
