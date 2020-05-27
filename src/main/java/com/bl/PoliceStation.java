package com.bl;

import com.bl.model.ParkingSpot;
import com.bl.model.Vehicle;

import java.util.ArrayList;

public class PoliceStation {

    private ParkingManager manager;

    public void addManager(ParkingManager parkingManager) {
        this.manager= parkingManager;
    }

    public ArrayList<ParkingSpot> getCar(Vehicle.COLOR color) {
        return manager.getCarLocationByColor(color);
    }

    public ArrayList<Vehicle> getCar(Vehicle.COLOR color, Vehicle.MAKE maker) {
        return manager.getCarByMakeAndColor(color,maker);
    }
}
