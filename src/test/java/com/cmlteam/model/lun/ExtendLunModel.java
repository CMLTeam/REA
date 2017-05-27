package com.cmlteam.model.lun;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import openchat.util.JsonUtil;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateException;

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
			Files.write(Paths.get(String.format("building_%d.json", id)), fetchBuilding(id).getBytes());
			System.out.printf("building %d processed\n", id);
		}
	}

	private static String fetchBuilding(long id) throws IOException {
		String getUrl = String.format("https://novostroyki.lun.ua/map-search/get-building-by-id?id=%d", id);
		OkHttpClient client = getUnsafeOkHttpClient();
		Request request = new Request.Builder().url(getUrl).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	private static OkHttpClient getUnsafeOkHttpClient() {
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						@Override
						public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}

						@Override
						public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}

						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return new java.security.cert.X509Certificate[]{};
						}
					}
			};

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			OkHttpClient okHttpClient = builder.build();
			return okHttpClient;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
