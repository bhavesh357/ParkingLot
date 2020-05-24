package com.bl;

import com.bl.exception.ParkingLotException;
import com.bl.model.AiportSecurity;
import com.bl.model.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*******************************************************************
 * author: Bhavesh Kadam
 */
public class ParkingLot {
    private final int capacity;
    private List cars;
    private boolean fullSign;

    /**
     * Costructor for Parking Lot
     * @param capacity
     */
    public ParkingLot(int capacity) {
        this.cars = new ArrayList<Car>();
        this.capacity = capacity;
        fullSign = false;
    }

    /**
     * Method to park car
     * @param car
     * @return token
     */
    public void park(Car car) {
        if(cars.contains(car)){
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_ALREADYPARKED);
        }
        if(isFull()){
            throw new ParkingLotException(ParkingLotException.ErrorType.LOT_FULL);
        }
        cars.add(car);
        isFull();
    }

    /**
     * method to unpark car
     * @param car
     * @return car
     */
    public Car unPark(Car car) {
        if(!cars.contains(car)){
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
        }
        cars.remove(car);
        isFull();
        return car;
    }

    /**
     * to check is parking lot is full
     */
    public boolean isFull() {
        if (capacity==cars.size()){
            this.fullSign = true;
        }
        return fullSign;
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
