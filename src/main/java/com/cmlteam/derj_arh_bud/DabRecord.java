package com.cmlteam.derj_arh_bud;

public class DabRecord {
    public int year;
    public int month;
    public String number;
    public String idabk;
    public String document;
    public String object;
    public DabCategory category;
    public String zamovnyk;
    public String tehNaglyad;
    public String proektuvalnyk;
    public String authorNaglyad;
    public String pidryadnyk;
    public String information;

    public DabRecord() {
    }

    public DabRecord(int year, int month, String number, String idabk, String document, String object, DabCategory category, String zamovnyk, String tehNaglyad, String proektuvalnyk, String authorNaglyad, String pidryadnyk, String information) {
        this.year = year;
        this.month = month;
        this.number = number;
        this.idabk = idabk;
        this.document = document;
        this.object = object;
        this.category = category;
        this.zamovnyk = zamovnyk;
        this.tehNaglyad = tehNaglyad;
        this.proektuvalnyk = proektuvalnyk;
        this.authorNaglyad = authorNaglyad;
        this.pidryadnyk = pidryadnyk;
        this.information = information;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIdabk() {
        return idabk;
    }

    public void setIdabk(String idabk) {
        this.idabk = idabk;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public DabCategory getCategory() {
        return category;
    }

    public void setCategory(DabCategory category) {
        this.category = category;
    }

    public String getZamovnyk() {
        return zamovnyk;
    }

    public void setZamovnyk(String zamovnyk) {
        this.zamovnyk = zamovnyk;
    }

    public String getTehNaglyad() {
        return tehNaglyad;
    }

    public void setTehNaglyad(String tehNaglyad) {
        this.tehNaglyad = tehNaglyad;
    }

    public String getProektuvalnyk() {
        return proektuvalnyk;
    }

    public void setProektuvalnyk(String proektuvalnyk) {
        this.proektuvalnyk = proektuvalnyk;
    }

    public String getAuthorNaglyad() {
        return authorNaglyad;
    }

    public void setAuthorNaglyad(String authorNaglyad) {
        this.authorNaglyad = authorNaglyad;
    }

    public String getPidryadnyk() {
        return pidryadnyk;
    }

    public void setPidryadnyk(String pidryadnyk) {
        this.pidryadnyk = pidryadnyk;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
