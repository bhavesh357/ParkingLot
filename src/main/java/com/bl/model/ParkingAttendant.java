package com.bl.model;

import com.bl.ParkingLot;

public class ParkingAttendant {
    /**
     * method to park the car
     * @param preferredLot
     * @param vehicle
     */
    public void park(ParkingLot preferredLot, Vehicle vehicle) {
        preferredLot.park(vehicle);
    }
}
