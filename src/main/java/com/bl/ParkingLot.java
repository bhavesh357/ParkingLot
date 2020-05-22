package com.bl;

import com.bl.model.Car;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private final int capacity;
    private Car[] cars;

    public ParkingLot(int capacity) {
        this.cars = new Car[capacity];
        this.capacity = capacity;
    }

    public int park(Car car) {
        for(int i=0;i<this.capacity;i++){
            if(cars[i]==null){
                cars[i]=car;
                return i+1;
            }
        }
        return 0;
    }

    public Car unpark(int token) {
        return cars[token-1];
    }

    public boolean isFull() {
        return false;
    }
}
