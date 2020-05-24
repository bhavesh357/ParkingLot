package com.bl.model;

import com.bl.ParkingLot;

public class ParkingAttendant {
    public void park(ParkingLot preferredLot, Car car) {
        preferredLot.park(car);
    }
}
