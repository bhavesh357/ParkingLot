package com.bl;

import com.bl.model.AiportSecurity;
import com.bl.model.Car;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private final int capacity;
    private Car[] cars;
    private int stored;
    private boolean fullSign;

    public ParkingLot(int capacity) {
        this.cars = new Car[capacity];
        this.capacity = capacity;
        this.stored=0;
        fullSign = false;
    }

    public int park(Car car) {
        for(int i=0;i<this.capacity;i++){
            if(cars[i]==null){
                cars[i]=car;
                stored++;
                return i+1;
            }
        }
        return 0;
    }

    public Car unpark(int token) {
        Car car = cars[token - 1];
        cars[token-1]=null;
        return car;
    }

    public void isFull() {
        if (capacity==stored){
            this.fullSign = true;
        }
    }

    public boolean redirectStaff(AiportSecurity aiportSecurity) {
        isFull();
        return aiportSecurity.redirectStaff(fullSign);
    }

    public boolean isSignUp() {
        return fullSign;
    }
}
