package com.bl.model;

import java.util.Date;

public class Car {
    private Date time;
    public boolean isHandicapped=false;

    public Date getParkedTime() {
        return time;
    }

    public void setParkedTime(Date time) {
        this.time =time;
    }

    public void setHandicappedDriver() {
        this.isHandicapped = true;
    }
}
