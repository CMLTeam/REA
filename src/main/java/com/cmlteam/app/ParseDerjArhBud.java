package com.cmlteam.app;

import java.io.File;

public class ParseDerjArhBud {
    static int[] years = {2015, 2016, 2017};

    public static void main(String[] args) {
        String folder = args[0];

        File outFolder = new File(folder);
        if (outFolder.exists() && !outFolder.mkdirs()) {
            throw new RuntimeException("Can't create out folder");
        }

        File out = new File(outFolder, "derj_arh_bud.txt");

        String url = "http://91.205.16.115/declarate/list.php?sort=num&order=DESC";

        for (int year : years) {
            System.out.println("Processing " + year + "...");

        }
    }
}
