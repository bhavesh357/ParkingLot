package com.bl.model;

import java.util.Date;

public class Vehicle {
    public final Type type;
    private Date time;
    public boolean isHandicapped=false;
    private ParkingSpot spot;
    private COLOR color;

    public Vehicle() {
        this.type=Type.Car;
    }

    public Vehicle(Type type) {
        this.type = type;
    }

    public Date getParkedTime() {
        return time;
    }

    public void setParkedTime(Date time) {
        this.time =time;
    }

    public void setHandicappedDriver() {
        this.isHandicapped = true;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }

    public COLOR getColor() {
        return color;
    }

    public enum Type {Car, Truck}

    public enum COLOR {WHITE}
}
