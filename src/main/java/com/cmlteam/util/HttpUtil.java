package com.cmlteam.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    public static String ajax(String url, String body, Header... headers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (headers != null && headers.length > 0) {
            httpPost.setHeaders(headers);
        }
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        return execReturnString(httpClient, httpPost);
    }

    public static String fetchPlainText(String url, Header... headers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        if (headers != null && headers.length > 0) {
            httpGet.setHeaders(headers);
        }
        return execReturnString(httpClient, httpGet);
    }

    public static String fetchPlainTextViaPost(String url, Map<String, String> params, Header... headers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> postParameters;
        postParameters = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            postParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
        if (headers != null && headers.length > 0) {
            httpPost.setHeaders(headers);
        }
        
        return execReturnString(httpClient, httpPost);
    }

    private static String execReturnString(CloseableHttpClient httpClient, HttpRequestBase httpReq) throws IOException {
        try (CloseableHttpResponse response = httpClient.execute(httpReq)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                try {
                    return IOUtils.toString(entity.getContent(), "UTF-8");
                } finally {
                    EntityUtils.consume(entity);
                }
            } else {
                throw new IOException(response.getStatusLine().getReasonPhrase());
            }
        }
    }
}
