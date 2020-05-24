package com.bl;

import com.bl.exception.ParkingLotException;
import com.bl.model.Car;
import com.bl.model.ParkingLotObeserver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*******************************************************************
 * author: Bhavesh Kadam
 */
public class ParkingLot {
    private int capacity; //total limit of lot
    public final List<Car> cars; // list of stored cars
    private boolean fullSign; //sign that says lot full
    public final List<ParkingLotObeserver> observers; //owner or security who need to know if lot is full

    /**
     * Costructor for Parking Lot
     * @param capacity //total limit of lot
     */
    public ParkingLot(int capacity) {
        this.cars = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.capacity = capacity;
        fullSign = false;
    }

    /**
     * method to add observers to Parking Lot
     * @param observer // who is observing
     */
    public void addObserver(ParkingLotObeserver observer) {
        if(!observers.contains(observer)){
            this.observers.add(observer);
        }
    }

    /**
     * Method to park car
     * @param car which car is to be stored
     */
    public void park(Car car) {
        if(cars.contains(car)){
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_ALREADY_PARKED);
        }
        if(isFull()){
            throw new ParkingLotException(ParkingLotException.ErrorType.LOT_FULL);
        }
        Date time = Calendar.getInstance().getTime();
        car.setParkedTime(time);
        cars.add(car);
        isFull();
    }

    /**
     * method to unpark car
     * @param car //which car to unpark
     * @return car //unparke car
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
        }else{
            this.fullSign = false;
        }
        return fullSign;
    }

    public void setCapacity(int capacity) {
        this.capacity=capacity;
    }
}
