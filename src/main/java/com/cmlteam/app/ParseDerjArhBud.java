package com.cmlteam.app;

import com.cmlteam.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ParseDerjArhBud {
    //    static int[] years = {2015, 2016, 2017};
    static int[] years = {2017};
    private static final String KYIV_OBL_REGION_ID = "10";

    public static void main(String[] args) {
        String folder = args[0];

        File outFolder = new File(folder);
        if (outFolder.exists() && !outFolder.mkdirs()) {
            throw new RuntimeException("Can't create out folder");
        }

        File out = new File(outFolder, "derj_arh_bud.txt");

        String url = "http://91.205.16.115/declarate/list.php?sort=num&order=DESC";

        for (int year : years) {
            for (int month = 1; month <= 12; month++) {
                int lastPage = -1;
                int pageNo = 1;

                while (lastPage == -1 || pageNo <= lastPage) {
                    System.out.println();
                    System.out.println("Processing " + year + "...");
                    System.out.println();

                    Map<String, String> params = new HashMap<>();
                    params.put("filter[regob]", KYIV_OBL_REGION_ID);
                    params.put("filter[date]", "" + year);
                    params.put("filter[date2]", prepZeroes(month));

                    String page = HttpUtil.fetchPlainTextViaPost(url + "&page=" + pageNo, params);
//                    System.out.println(page);

                    Document document = Jsoup.parse(page);
                    if (lastPage == -1) {
                        lastPage = Integer.parseInt(document
                                .select("#pages > a:last-of-type")
                                .attr("href")
                                .split("page=")[1]);

                        System.out.println("Last page= " + lastPage);
                        break;// TODO
                    }

                    pageNo++;
                }
            }
        }
    }

    private static String prepZeroes(int i) {
        if (i < 10)
            return "0" + i;
        return "" + i;
    }
}
