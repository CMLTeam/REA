package com.cmlteam.app;

import com.cmlteam.derj_arh_bud.DabRecord;
import com.cmlteam.util.JsonUtil;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DerjArhBudProcess {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rea";
    public static final String DB_USER = "rea";
    public static final String DB_PASS = "rea";
    public static final String FILE = "/home/xonix/proj/REA/src/main/resources/derj_arh_bud.txt";

    public static void main(String[] args) {
        PoolProperties p = new PoolProperties();
        p.setUrl(DB_URL);
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername(DB_USER);
        p.setPassword(DB_PASS);
        DataSource dataSource = new DataSource(p);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        long total = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE)))) {
            String row;
            while ((row = reader.readLine()) != null) {
                total++;
                DabRecord dabRecord = JsonUtil.parseJson(row, DabRecord.class);

                try {
                    // TODO batch
                    jdbcTemplate.update("insert into dabi (year,month,number,idabk,document,object,address," +
                                    "address_city,address_district,address_street,address_streetno,address_flat,"+//"category," +
                                    "zamovnyk,teh_naglyad,proektuvalnyk,author_naglyad,pidryadnyk,information) values " +
                                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                            dabRecord.getYear(),
                            dabRecord.getMonth(),
                            dabRecord.getNumber(),
                            dabRecord.getIdabk(),
                            dabRecord.getDocument(),
                            dabRecord.getObject(),
                            dabRecord.getAddress(),
                            dabRecord.getAddressCity(),
                            dabRecord.getAddressDistrict(),
                            dabRecord.getAddressStreet(),
                            dabRecord.getAddressStreetNo(),
                            dabRecord.getAddressFlatNo(),
//                            dabRecord.getCategory().getNo(),
                            dabRecord.getZamovnyk(),
                            dabRecord.getTehNaglyad(),
                            dabRecord.getProektuvalnyk(),
                            dabRecord.getAuthorNaglyad(),
                            dabRecord.getPidryadnyk(),
                            dabRecord.getInformation());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(dabRecord);
                    return;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Inserted: " + total);
    }
}
