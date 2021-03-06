package com.bl;

import com.bl.exception.ParkingLotException;
import com.bl.model.ParkingSpot;
import com.bl.model.Vehicle;
import com.bl.model.ParkingLotObeserver;

import java.util.*;

/*******************************************************************
 * author: Bhavesh Kadam
 */
public class ParkingLot {
    private int capacity; //total limit of lot
    public final HashMap<ParkingSpot,Vehicle> parkingLot; // list of stored cars
    private boolean fullSign; //sign that says lot full
    public final List<ParkingLotObeserver> observers; //owner or security who need to know if lot is full

    /**
     * Costructor for Parking Lot
     *
     * @param capacity //total limit of lot
     */
    public ParkingLot(int capacity) {
        this.observers = new ArrayList<>();
        this.capacity = capacity;
        this.parkingLot = new HashMap<>();
        loadLot();
        fullSign = false;
    }

    /**
     * method to load lot with null parking spots
     */
    private void loadLot() {
        String rows="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=1;i<=capacity;i++){

            String row=""+rows.charAt(i/3);
            parkingLot.put(new ParkingSpot(this,row,i%3),null);
        }
    }

    /**
     * method to add observers to Parking Lot
     *
     * @param observer // who is observing
     */
    public void addObserver(ParkingLotObeserver observer) {
        if (!observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    /**
     * Method to park car
     *
     * @param vehicle which car is to be stored
     */
    public void park(Vehicle vehicle) {
        if (parkingLot.values().contains(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_ALREADY_PARKED);
        }
        if (isFull()) {
            throw new ParkingLotException(ParkingLotException.ErrorType.LOT_FULL);
        }
        Date time = Calendar.getInstance().getTime();
        vehicle.setParkedTime(time);
        ParkingSpot spot = getClosestSpot();
        parkingLot.put(spot,vehicle);
        vehicle.setSpot(spot);
        isFull();
    }

    /**
     * method to get the nearest empty parking lot
     * @return parking spot
     */
    private ParkingSpot getClosestSpot() {
        for(ParkingSpot s: parkingLot.keySet()){
            if(parkingLot.get(s)==null){
                return s;
            }
        }
        return null;
    }

    /**
     * method to unpark car
     *
     * @param vehicle //which car to unpark
     * @return car //unparke car
     */
    public Vehicle unPark(Vehicle vehicle) {
        if (!parkingLot.values().contains(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
        }
        parkingLot.put(vehicle.getSpot(),null);
        vehicle.setSpot(null);
        isFull();
        return vehicle;
    }

    /**
     * to check if parking lot is full
     */
    public boolean isFull() {
        for(ParkingSpot p: parkingLot.keySet()){
            if(parkingLot.get(p)==null){
                return false;
            }
        }
        return true;
    }

    /**
     * method to get no of cars parked
     * @return
     */
    public int getCurrentsize() {
        int count=0;
        for(ParkingSpot p: parkingLot.keySet()){
            if(parkingLot.get(p)!=null){
                count++;
            }
        }
        return count;
    }

    /**
     * method to get car location bby color
     * @param color
     * @return parking spot
     */
    public ArrayList<ParkingSpot> getCarLocationByColor(Vehicle.COLOR color) {
        ArrayList<ParkingSpot> locations= new ArrayList<ParkingSpot>();
        for(ParkingSpot ps: parkingLot.keySet()){
            if(parkingLot.get(ps)!=null){
                if (parkingLot.get(ps).getColor()==color){
                    locations.add(ps);
                }
            }
        }
        return locations;
    }

    /**
     * method to get car by maker and color
     * @param color
     * @param maker
     * @return car
     */

    public ArrayList<Vehicle> getCarLocationByMakeAndColor(Vehicle.COLOR color, Vehicle.MAKE maker) {
        ArrayList<Vehicle> cars= new ArrayList<Vehicle>();
        for(ParkingSpot ps: parkingLot.keySet()){
            if(parkingLot.get(ps)!=null){
                if (parkingLot.get(ps).getColor()==color && parkingLot.get(ps).getMaker()==maker){
                    cars.add(parkingLot.get(ps));
                }
            }
        }
        return cars;
    }

    /**
     * method to get list of cars by maker
     * @param maker
     * @return list of cars
     */
    public Collection<Vehicle> getCarLocationByMaker(Vehicle.MAKE maker) {
        ArrayList<Vehicle> cars= new ArrayList<Vehicle>();
        for(ParkingSpot ps: parkingLot.keySet()){
            if(parkingLot.get(ps)!=null){
                if ( parkingLot.get(ps).getMaker()==maker){
                    cars.add(parkingLot.get(ps));
                }
            }
        }
        return cars;
    }

    /**
     * method to get car by park time
     * @param cutoffTime
     * @return list of cars
     */
    public Collection<Vehicle> getCarLocationByTime(Date cutoffTime) {
        ArrayList<Vehicle> cars= new ArrayList<Vehicle>();
        for(ParkingSpot ps: parkingLot.keySet()){
            if(parkingLot.get(ps)!=null){
                if ( parkingLot.get(ps).getParkedTime().compareTo(cutoffTime)>0 ){
                    cars.add(parkingLot.get(ps));
                }
            }
        }
        return cars;
    }

    /**
     * method to get car by row and handicapped driver
     * @param row
     * @return list of car
     */
    public ArrayList<Vehicle> getCarByRowAndHandicapped(char row) {
        ArrayList<Vehicle> cars= new ArrayList<Vehicle>();
        for(ParkingSpot ps: parkingLot.keySet()){
            if(parkingLot.get(ps)!=null){
                if ( ps.getRow()==row  && parkingLot.get(ps).isHandicapped ){
                    cars.add(parkingLot.get(ps));
                }
            }
        }
        return cars;
    }

    /**
     * method to get all parked car
     * @return list of cars
     */
    public ArrayList<Vehicle> getAllCars() {
        ArrayList<Vehicle> cars= new ArrayList<Vehicle>();
        for(ParkingSpot ps: parkingLot.keySet()){
            if(parkingLot.get(ps)!=null){
                cars.add(parkingLot.get(ps));
            }
        }
        return cars;
    }
}

