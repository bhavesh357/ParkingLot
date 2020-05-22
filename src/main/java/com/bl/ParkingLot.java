package com.bl;

import com.bl.model.Car;

public class ParkingLot {


    private Car car;

    public boolean park(Car car) {
        this.car = car;
        return true;
    }

    public Car unpark() {
        return car;
    }
}
