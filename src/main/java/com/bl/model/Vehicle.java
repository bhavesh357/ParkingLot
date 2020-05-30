package com.bl.model;

import java.util.Date;

public class Vehicle {
    public final Type type;
    private Date time;
    public boolean isHandicapped=false;
    private ParkingSpot spot;
    private COLOR color;
    private MAKE maker;

    public Vehicle() {
        this.type=Type.Car;
    }

    public Vehicle(Type type) {
        this.type = type;
    }

    /**
     * method to get parked time
     * @return time
     */
    public Date getParkedTime() {
        return time;
    }

    /**
     * method to set time
     * @param time
     */
    public void setParkedTime(Date time) {
        this.time =time;
    }

    /**
     * method to set handicapped driver
     */
    public void setHandicappedDriver() {
        this.isHandicapped = true;
    }

    /**
     * method to set parking spot
     * @param spot
     */
    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    /**
     * method to get parking spot
     * @return parkingSpot
     */
    public ParkingSpot getSpot() {
        return spot;
    }

    /**
     * method to set color
     * @param color
     */
    public void setColor(COLOR color) {
        this.color = color;
    }

    /**
     * method to get color
     * @return
     */
    public COLOR getColor() {
        return color;
    }

    /**
     * method to set maker
     * @param maker
     */
    public void setMaker(MAKE maker) {
        this.maker = maker;
    }

    /**
     * method to get maker
     * @return
     */
    public MAKE getMaker() {
        return maker;
    }

    /**
     * Enum for type of vehicle
     */
    public enum Type {Car, Truck}

    /**
     * enum for vehicle color
     */
    public enum COLOR {BLUE, WHITE}

    /**
     * enum for vehicle maker
     */
    public enum MAKE {BMW, TOYOTA}
}
