package com.cmlteam.app;

import com.cmlteam.derj_arh_bud.DabRecord;
import com.cmlteam.util.JsonUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DerjArhBudProcess {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/rea";
    public static final String DB_USER = "rea";
    public static final String DB_PASS = "rea";
    public static final String FILE = "/home/xonix/proj/REA/src/main/resources/derj_arh_bud.txt";

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASS);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE)))){
            String row;
            while((row = reader.readLine()) != null) {
                DabRecord dabRecord = JsonUtil.parseJson(row, DabRecord.class);

                System.out.println(dabRecord);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
