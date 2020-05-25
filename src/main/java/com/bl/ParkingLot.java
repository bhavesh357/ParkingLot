package com.bl;

import com.bl.exception.ParkingLotException;
import com.bl.model.Vehicle;
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
    public final List<Vehicle> vehicles; // list of stored cars
    private boolean fullSign; //sign that says lot full
    public final List<ParkingLotObeserver> observers; //owner or security who need to know if lot is full

    /**
     * Costructor for Parking Lot
     * @param capacity //total limit of lot
     */
    public ParkingLot(int capacity) {
        this.vehicles = new ArrayList<>();
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
     * @param vehicle which car is to be stored
     */
    public void park(Vehicle vehicle) {
        if(vehicles.contains(vehicle)){
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_ALREADY_PARKED);
        }
        if(isFull()){
            throw new ParkingLotException(ParkingLotException.ErrorType.LOT_FULL);
        }
        Date time = Calendar.getInstance().getTime();
        vehicle.setParkedTime(time);
        vehicles.add(vehicle);
        isFull();
    }

    /**
     * method to unpark car
     * @param vehicle //which car to unpark
     * @return car //unparke car
     */
    public Vehicle unPark(Vehicle vehicle) {
        if(!vehicles.contains(vehicle)){
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
        }
        vehicles.remove(vehicle);
        isFull();
        return vehicle;
    }

    /**
     * to check is parking lot is full
     */
    public boolean isFull() {
        if (capacity== vehicles.size()){
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
