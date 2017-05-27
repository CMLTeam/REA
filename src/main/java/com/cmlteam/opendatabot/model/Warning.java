package com.cmlteam.opendatabot.model;

import java.util.List;

public class Warning {
    private String database_date;
    private String icon;
    private String text;
    private String type;
    private String value;

    private List<Decision> decisions;

    public Warning() {
    }

    public String getDatabase_date() {
        return database_date;
    }

    public void setDatabase_date(String database_date) {
        this.database_date = database_date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }
}
