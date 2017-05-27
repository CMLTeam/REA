package com.cmlteam.opendatabot;

import com.cmlteam.opendatabot.model.Company;
import com.cmlteam.util.JsonUtil;

public class Test1 {
    public static void main(String[] args) {
        OpenDataBotApi openDataBotApi = new OpenDataBotApi();

        Company company = openDataBotApi.getFullCompanyInfo("33422484");

        System.out.println(JsonUtil.prettyPrintJson(JsonUtil.toJsonString(company)));
    }
}
