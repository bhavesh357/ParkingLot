package com.bl;

import com.bl.model.AiportSecurity;
import com.bl.model.Car;

import java.util.ArrayList;
import java.util.HashMap;

/*******************************************************************
 * author: Bhavesh Kadam
 */
public class ParkingLot {
    private final int capacity;
    private Car[] cars;
    private int stored;
    private boolean fullSign;

    /**
     * Costructor for Parking Lot
     * @param capacity
     */
    public ParkingLot(int capacity) {
        this.cars = new Car[capacity];
        this.capacity = capacity;
        this.stored=0;
        fullSign = false;
    }

    /**
     * Method to park car
     * @param car
     * @return token
     */
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

    /**
     * method to unpark car
     * @param token
     * @return car
     */
    public Car unpark(int token) {
        Car car = cars[token - 1];
        cars[token-1]=null;
        return car;
    }

    /**
     * to check is parking lot is full
     */
    public void isFull() {
        if (capacity==stored){
            this.fullSign = true;
        }
    }

    /**
     * ask airport security to shift staff if full
     * @param aiportSecurity
     * @return is staff moved or not
     */
    public boolean redirectStaff(AiportSecurity aiportSecurity) {
        isFull();
        return aiportSecurity.redirectStaff(fullSign);
    }

    /**
     * method to check if full sign is up or not
     * @return sign status
     */
    public boolean isSignUp() {
        return fullSign;
    }
}
