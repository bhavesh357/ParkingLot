package com.bl.model;

import com.bl.ParkingLot;

public class ParkingSpot {
    private final int number;
    private final ParkingLot lot;

    public ParkingSpot(ParkingLot parkingLot, int number) {
        this.lot=parkingLot;
        this.number = number;
    }
}
