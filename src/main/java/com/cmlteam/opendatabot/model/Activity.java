package com.cmlteam.opendatabot.model;

public class Activity {
    private boolean is_primary;
    private String name;

    public Activity() {
    }

    public boolean isIs_primary() {
        return is_primary;
    }

    public void setIs_primary(boolean is_primary) {
        this.is_primary = is_primary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
