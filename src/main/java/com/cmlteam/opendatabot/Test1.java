package com.cmlteam.opendatabot;

import com.cmlteam.opendatabot.model.Company;
import com.cmlteam.opendatabot.model.CompanyBasic;
import com.cmlteam.util.JsonUtil;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        OpenDataBotApi openDataBotApi = new OpenDataBotApi();


        System.out.println();
        System.out.println(" ---- FULL COMPANY ----- ");
        System.out.println();
        Company company = openDataBotApi.getFullCompanyInfo("33422484");
        System.out.println(JsonUtil.prettyPrintJson(JsonUtil.toJsonString(company)));

        System.out.println();
        System.out.println(" ---- SEARCH ----- ");
        System.out.println();
        List<CompanyBasic> list = openDataBotApi.search("іванкін дніпро");
        System.out.println(JsonUtil.prettyPrintJson(JsonUtil.toJsonString(list)));
    }
}
