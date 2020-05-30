package com.bl;

import com.bl.model.ParkingSpot;
import com.bl.model.Vehicle;

import java.util.ArrayList;

public class PoliceStation {

    private ParkingManager manager;

    /**
     * method to add manager
     * @param parkingManager
     */
    public void addManager(ParkingManager parkingManager) {
        this.manager= parkingManager;
    }

    /**
     * method to get car location bby color
     * @param color
     * @return parking spot
     */
    public ArrayList<ParkingSpot> getCar(Vehicle.COLOR color) {
        return manager.getCarLocationByColor(color);
    }
    /**
     * method to get car by maker and color
     * @param color
     * @param maker
     * @return car
     */

    public ArrayList<Vehicle> getCar(Vehicle.COLOR color, Vehicle.MAKE maker) {
        return manager.getCarByMakeAndColor(color,maker);
    }
    /**
     * method to get list of cars by maker
     * @param maker
     * @return list of cars
     */

    public ArrayList<Vehicle> getCar(Vehicle.MAKE maker) {
        return manager.getCarByMaker(maker);
    }

    /**
     * method to get car by park time
     * @param time
     * @return list of cars
     */
    public ArrayList<Vehicle> getCar(int time) {
        return manager.getCarByTime(time);
    }

    /**
     * method to get car by row and handicapped driver
     * @param rows
     * @param isHandicapped
     * @return list of car
     */
    public ArrayList<Vehicle> getCar(String rows, boolean isHandicapped) {
        return manager.getCarByRowAndHandicapped(rows);
    }

    /**
     * method to get all parked car
     * @return list of cars
     */
    public ArrayList<Vehicle> getCar() {
        return manager.getAllCars();
    }
}
