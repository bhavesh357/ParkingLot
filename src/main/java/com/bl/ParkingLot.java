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
    public final HashMap<ParkingSpot,Vehicle> vehicles; // list of stored cars
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
        this.vehicles = new HashMap<>();
        loadLot();
        fullSign = false;
    }

    private void loadLot() {
        for(int i=1;i<=capacity;i++){
            vehicles.put(new ParkingSpot(this,i),null);
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
        if (vehicles.values().contains(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_ALREADY_PARKED);
        }
        if (isFull()) {
            throw new ParkingLotException(ParkingLotException.ErrorType.LOT_FULL);
        }
        Date time = Calendar.getInstance().getTime();
        vehicle.setParkedTime(time);
        ParkingSpot spot = getClosestSpot();
        vehicles.put(spot,vehicle);
        vehicle.setSpot(spot);
        isFull();
    }

    private ParkingSpot getClosestSpot() {
        for(ParkingSpot s: vehicles.keySet()){
            if(vehicles.get(s)==null){
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
        if (!vehicles.values().contains(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
        }
        vehicles.put(vehicle.getSpot(),null);
        vehicle.setSpot(null);
        isFull();
        return vehicle;
    }

    /**
     * to check if parking lot is full
     */
    public boolean isFull() {
        for(ParkingSpot p: vehicles.keySet()){
            if(vehicles.get(p)==null){
                return false;
            }
        }
        return true;
    }

    public int getCurrentsize() {
        int count=0;
        for(ParkingSpot p: vehicles.keySet()){
            if(vehicles.get(p)!=null){
                count++;
            }
        }
        return count;
    }

    public ArrayList<ParkingSpot> getCarLocationByColor(Vehicle.COLOR color) {
        ArrayList<ParkingSpot> locations= new ArrayList<ParkingSpot>();
        for(ParkingSpot ps: vehicles.keySet()){
            if(vehicles.get(ps)!=null){
                if (vehicles.get(ps).getColor()==color){
                    locations.add(ps);
                }
            }
        }
        return locations;
    }

    public ArrayList<Vehicle> getCarLocationByMakeAndColor(Vehicle.COLOR color, Vehicle.MAKE maker) {
        ArrayList<Vehicle> cars= new ArrayList<Vehicle>();
        for(ParkingSpot ps: vehicles.keySet()){
            if(vehicles.get(ps)!=null){
                if (vehicles.get(ps).getColor()==color && vehicles.get(ps).getMaker()==maker){
                    cars.add(vehicles.get(ps));
                }
            }
        }
        return cars;
    }
}

