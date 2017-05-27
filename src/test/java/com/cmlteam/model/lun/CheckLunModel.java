package com.cmlteam.model.lun;

import openchat.util.JsonUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author vgorin
 *         file created on 5/27/17 6:57 PM
 */


public class CheckLunModel {
	public static void main(String[] args) throws IOException, URISyntaxException {
		String json = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("lundb_1.json").toURI())));
		LunModel model = JsonUtil.parseJson(json, LunModel.class);
		System.out.println(model);
	}
}
