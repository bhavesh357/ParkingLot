package com.bl.model;

import com.bl.ParkingLot;

public class ParkingSpot {
    private final int number;
    private final ParkingLot lot;
    private final String row;
    public ParkingSpot(ParkingLot parkingLot, String row, int number) {
        this.row=row;
        this.lot=parkingLot;
        this.number=number;
    }

    public char getRow() {
        return row.charAt(0);
    }
}
