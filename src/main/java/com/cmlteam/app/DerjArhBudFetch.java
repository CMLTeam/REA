package com.cmlteam.app;

import com.cmlteam.derj_arh_bud.DabCategory;
import com.cmlteam.derj_arh_bud.DabRecord;
import com.cmlteam.util.HttpUtil;
import com.cmlteam.util.JsonUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DerjArhBudFetch {
    static int[] years = {2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017};
    //    static int[] years = {2017};
    private static final String KYIV_OBL_REGION_ID = "26";

    public static void main(String[] args) throws IOException {
        String folder = args[0];

        File outFolder = new File(folder);
        if (!outFolder.exists() && !outFolder.mkdirs()) {
            throw new RuntimeException("Can't create out folder");
        }

        File outF = new File(outFolder, "derj_arh_bud.txt");
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(outF));

        String url = "http://91.205.16.115/declarate/list.php?sort=num&order=DESC";

        for (int year : years) {
            for (int month = 1; month <= 12; month++) {
                int lastPage = -1;
                int pageNo = 1;

                while (lastPage == -1 || pageNo <= lastPage) {
                    System.out.println();
                    System.out.println("Processing " + year + " month=" + month + " page=" + pageNo + "...");
                    System.out.println();

                    Map<String, String> params = new HashMap<>();
                    params.put("filter[regob]", KYIV_OBL_REGION_ID);
                    params.put("filter[date]", "" + year);
                    params.put("filter[date2]", prepZeroes(month));

                    String page = HttpUtil.fetchPlainTextViaPost(url + "&page=" + pageNo, params);
//                    System.out.println(page);

                    Document document = Jsoup.parse(page);

                    Elements elements = document.select(".listTable tr");

                    for (Element element : elements) {
                        if (!element.attr("style").contains("font-weight: normal;"))
                            continue;

                        Elements tds = element.select("td");

                        List<String> row = new ArrayList<>();
                        for (Element td : tds) {
                            String text = td.text().replaceAll("(^\\h*)|(\\h*$)", "");
                            row.add(text);
                        }

                        DabRecord dabRecord = new DabRecord(
                                year,
                                month,
                                row.get(0),
                                row.get(1),
                                row.get(2),
                                row.get(3),
                                DabCategory.of(row.get(4)),
                                row.get(5),
                                row.get(6),
                                row.get(7),
                                row.get(8),
                                row.get(9),
                                row.get(10));

                        String rowAsJson = JsonUtil.toJsonString(dabRecord);
//                        System.out.println(rowAsJson);
//                        System.out.println(JsonUtil.prettyPrintJson(rowAsJson));
                        printWriter.println(rowAsJson);
//                        return;
                    }

                    if (lastPage == -1) {
                        String href = document.select("#pages > a:last-of-type")
                                .attr("href");
//                        if (StringUtils.isBlank(href)) {
//                            System.out.println("That's all, folks");
//                            return;
//                        }
                        try {
                            lastPage = Integer.parseInt(href
                                    .split("page=")[1]);
                        } catch (Exception ex) {
                            System.out.println(ex);
                            break;
                        }

                        System.out.println("Last page= " + lastPage);
//                        break;// TODO
                    }

                    pageNo++;
                }

                printWriter.flush();
            }
        }
    }

    private static String prepZeroes(int i) {
        if (i < 10)
            return "0" + i;
        return "" + i;
    }
}
