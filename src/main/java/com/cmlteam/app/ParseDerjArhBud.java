package com.cmlteam.app;

import java.io.File;

public class ParseDerjArhBud {
    public static void main(String[] args) {
        String folder = args[0];

        File outFolder = new File(folder);
        if (outFolder.exists() && !outFolder.mkdirs())  {
            throw new RuntimeException("Can't create out folder");
        }

        String url = "http://91.205.16.115/declarate/list.php?sort=num&order=DESC";


    }
}
