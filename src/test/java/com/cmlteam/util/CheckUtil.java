package com.cmlteam.util;

import com.cmlteam.model.lun.Building;

import java.io.IOException;

/**
 * @author vgorin
 *         file created on 5/27/17 8:43 PM
 */


public class CheckUtil {
	public static void main(String[] args) {
		String address = args[0];
		System.out.printf("Looking for %s\n", address);
		Building building = null;
		try {
			building = LunUtil.getClosestBuilding(address);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		if(building != null) {
			System.out.printf("Closest building found: %s // %s\n", building.name, building.address);
		}
		else {
			System.out.println("No buildings found");
		}
	}
}
