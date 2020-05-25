package com.bl.model;

import com.bl.ParkingLot;

public class ParkingAttendant {
    public void park(ParkingLot preferredLot, Vehicle vehicle) {
        preferredLot.park(vehicle);
    }
}
