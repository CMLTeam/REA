package com.cmlteam.opendatabot;

import com.cmlteam.Cfg;
import com.cmlteam.opendatabot.model.Company;
import com.cmlteam.opendatabot.model.CompanyBasic;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

public class OpenDataBotApi {

    private final String apiKey;
    private final RestTemplate restTemplate;

    public static final String BASE_URL = "https://opendatabot.com/api/v1";
    public static final String URL_FULL_COMPANY = BASE_URL + "/fullcompany/";
    public static final String URL_SEARCH = BASE_URL + "/search/";

    public OpenDataBotApi(String apiKey) {
        this.apiKey = apiKey;
        restTemplate = new RestTemplate();
    }

    public OpenDataBotApi() {
        this(Cfg.getOpenDataBotApiKey());
    }

    private String buildSearchQuery(String query) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return URL_SEARCH + query + "?apiKey=" + apiKey;
    }

    private String buildFullCompanyQuery(String code) {
        return URL_FULL_COMPANY + code + "?apiKey=" + apiKey;
    }

    public List<CompanyBasic> search(String query) {
        CompanyBasic[] res = restTemplate.getForObject(buildSearchQuery(query), CompanyBasic[].class);
        return Arrays.asList(res);
    }

    public Company getFullCompanyInfo(String code) {
        return restTemplate.getForObject(buildFullCompanyQuery(code), Company.class);
    }
}
