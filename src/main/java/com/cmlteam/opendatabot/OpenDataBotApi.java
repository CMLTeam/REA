package com.cmlteam.opendatabot;

import com.cmlteam.Cfg;
import com.cmlteam.util.PropertyUtil;

public class OpenDataBotApi {

    private final String apiKey;

    public OpenDataBotApi(String apiKey) {
        this.apiKey = apiKey;
    }

    public OpenDataBotApi() {
        this(Cfg.getOpenDataBotApiKey());
    }
}
