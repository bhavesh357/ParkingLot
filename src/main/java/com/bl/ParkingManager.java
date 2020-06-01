package com.bl;


import com.bl.exception.ParkingLotException;
import com.bl.model.ParkingSpot;
import com.bl.model.Vehicle;
import com.bl.model.ParkingAttendant;
import com.bl.model.ParkingLotObeserver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ParkingManager {

    public List<ParkingLot> lots;
    public ParkingAttendant attendant;

    /**
     * constructor for parking manager on number of lots with 1 capacity
     * @param noOfLots
     */
    public ParkingManager(int noOfLots) {
        this(noOfLots,1);
    }

    /**
     * constructor for parking manager on number of lots with specified capacity
     * @param noOfLots
     * @param lotCapacity
     */
    public ParkingManager(int noOfLots, int lotCapacity) {
        lots = new ArrayList<>();
        for(int i=0; i<noOfLots;i++){
            lots.add(new ParkingLot(lotCapacity));
        }
        attendant = new ParkingAttendant();
    }

    /**
     * method to add observer of parking lots
     * @param observer
     */
    public void addObserver(ParkingLotObeserver observer) {
        for (ParkingLot p: lots){
            p.addObserver(observer);
        }
    }

    /**
     * method to park the vehicle
     * @param vehicle
     */
    public void park(Vehicle vehicle) {
        ParkingLot preferredLot=null;
        if(vehicle.type== Vehicle.Type.Truck){
            preferredLot= mostEmptyLot();
        }
        if(preferredLot==null){
            if(vehicle.isHandicapped ){
                preferredLot=getClosestLot();
            }else{
                preferredLot = getPreferredLot();
            }
        }
        attendant.park(preferredLot, vehicle);
        isFull();
    }

    /**
     * method to get closest parking lot
     * @return parkingLot
     */
    private ParkingLot getClosestLot() {
        for(int i=0;i<lots.size();i++){
            if(!lots.get(i).isFull()){
                return lots.get(i);
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.ALL_LOTS_FULL);
    }

    /**
     * method to get most empty lot
     * @return parkingLot
     */
    private ParkingLot mostEmptyLot() {
        ParkingLot mostEmpty = lots.get(0);
        int least=mostEmpty.getCurrentsize();
        for(ParkingLot p: lots){
            if(p.getCurrentsize()<least){
                least=p.getCurrentsize();
                mostEmpty=p;
            }
        }
        return mostEmpty;
    }

    /**
     * method to get best suitable lot
     * @return ParkingLot
     */
    private ParkingLot getPreferredLot() {
        ParkingLot closestLot = lots.get(0);
        int max= closestLot.getCurrentsize();
        boolean flag=true;
        for(int i=1;i<lots.size();i++){
            if (lots.get(i).getCurrentsize() != max) {
                flag = false;
                break;
            }
        }
        if(flag && !closestLot.isFull()){
            return closestLot;
        }
        for(int i=1;i<lots.size();i++){
            if(lots.get(i).getCurrentsize()<max){
                return lots.get(i);
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.ALL_LOTS_FULL);
    }

    /**
     * method to unpark the vehicle
     * @param vehicle
     * @return vehicle
     */
    public Vehicle unPark(Vehicle vehicle) {
        for(ParkingLot p: lots){
            if(p.parkingLot.values().contains(vehicle)){
                Vehicle vehicle1 = p.unPark(vehicle);
                isFull();
                return vehicle1;
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
    }

    /**
     * method to inform all obesrevers if lots full
     */
    public void isFull() {
        boolean allLotsFull = true;
        for(ParkingLot p: lots){
            if(!p.isFull()){
                allLotsFull=false;
            }
        }
        for(ParkingLot p: lots) {
            for (ParkingLotObeserver o : p.observers) {
                if (allLotsFull) {
                    o.capacityIsFull();
                } else {
                    o.capacityIsAvailable();
                }
            }
        }
    }

    /**
     * method to know which lot vehicle is parked at
     * @param vehicle
     * @return ParkingLot
     */
    public ParkingLot getParkedLot(Vehicle vehicle) {
        for(ParkingLot p: lots){
            if(p.parkingLot.values().contains(vehicle)){
                return p;
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
    }

    /**
     * method to get ParkingSpot by vehicle color
     * @param color
     * @return list of vehicle
     */
    public ArrayList<ParkingSpot> getCarLocationByColor(Vehicle.COLOR color) {
        ArrayList<ParkingSpot> locations = new ArrayList<>();
        for(ParkingLot p: lots){
            locations.addAll(p.getCarLocationByColor(color));
        }
        return locations;
    }
    /**
     * method to get Vehicle by vehicle color and maker
     * @param color
     * @param maker
     * @return list of vehicle
     */

    public ArrayList<Vehicle> getCarByMakeAndColor(Vehicle.COLOR color, Vehicle.MAKE maker) {
        ArrayList<Vehicle> locations = new ArrayList<>();
        for(ParkingLot p: lots){
            locations.addAll(p.getCarLocationByMakeAndColor(color,maker));
        }
        return locations;
    }
    /**
     * method to get ParkingSpot by vehicle maker
     * @param maker
     * @return list of vehicle
     */

    public ArrayList<Vehicle> getCarByMaker(Vehicle.MAKE maker) {
        ArrayList<Vehicle> cars = new ArrayList<>();
        for(ParkingLot p: lots){
            cars.addAll(p.getCarLocationByMaker(maker));
        }
        return cars;
    }
    /**
     * method to get Vehicle by parked time
     * @param time
     * @return list of vehicle
     */

    public ArrayList<Vehicle> getCarByTime(int time) {
        Date cutoffTime = Calendar.getInstance().getTime();
        cutoffTime.setTime(cutoffTime.getTime()-time);
        ArrayList<Vehicle> cars = new ArrayList<>();
        for(ParkingLot p: lots){
            cars.addAll(p.getCarLocationByTime(cutoffTime));
        }
        return cars;
    }


    /**
     * method to get Vehicle by parked rows
     * @param rows
     * @return list of vehicle
     */
    public ArrayList<Vehicle> getCarByRowAndHandicapped(String rows) {
        ArrayList<Vehicle> cars = new ArrayList<>();
        for (char c: rows.toCharArray()){
            for (ParkingLot p: lots){
                cars.addAll(p.getCarByRowAndHandicapped(c));
            }
        }
        return cars;
    }

    /**
     * method to get all parked vehicles
     * @return list of vehicle
     */
    public ArrayList<Vehicle> getAllCars() {
        ArrayList<Vehicle> cars = new ArrayList<>();
        for (ParkingLot p: lots){
            cars.addAll(p.getAllCars());
        }
        return cars;
    }
}
