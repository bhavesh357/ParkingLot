package com.bl.model;

public class AirportSecurity implements ParkingLotObeserver{
    private boolean isCapacityFull; // to know if capacity full

    /**
     * method to set capacity full
     */
    @Override
    public void capacityIsFull() {
        this.isCapacityFull = true;
    }
    /**
     * method to set capacity not full
     */
    @Override
    public void capacityIsAvailable() {
        this.isCapacityFull = false;
    }

    /**
     * method to get know if capacity full
     * @return isCapacityFull
     */
    public boolean isCapacityFull() {
        return isCapacityFull;
    }
}
