package com.cmlteam.model.lun;

import com.cmlteam.util.HttpUtil;
import openchat.util.JsonUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author vgorin
 *         file created on 5/27/17 9:25 PM
 */


public class ExtendLunModel {
	public static void main(String[] args) throws IOException, URISyntaxException {
		String json = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("lundb_1.json").toURI())));
		LunModel model = JsonUtil.parseJson(json, LunModel.class);
		System.out.println(model);

		for(Building building: model.buildings) {
			long id = building.id;
			Path p = Paths.get(String.format("~/DEVELOP/cml/REA/lunbuilding_%d.json", id));
			Files.write(p, fetchBuilding(id).getBytes());
			System.out.printf("building %d processed\n", id);
		}
	}

	private static String fetchBuilding(long id) throws IOException {
		String getUrl = String.format("https://novostroyki.lun.ua/map-search/get-building-by-id?id=%d", id);
		return HttpUtil.fetchPlainText(getUrl);
	}
}
