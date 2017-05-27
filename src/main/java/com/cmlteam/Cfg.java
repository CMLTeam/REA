package com.cmlteam;

import com.cmlteam.util.PropertyUtil;

public final class Cfg {
    private Cfg() {
    }

    public static String getOpenDataBotApiKey() {
        return PropertyUtil.getProperty("open_data_bot_key");
    }
}
