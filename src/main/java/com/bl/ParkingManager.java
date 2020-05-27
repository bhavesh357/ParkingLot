package com.bl;


import com.bl.exception.ParkingLotException;
import com.bl.model.ParkingSpot;
import com.bl.model.Vehicle;
import com.bl.model.ParkingAttendant;
import com.bl.model.ParkingLotObeserver;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager {

    public List<ParkingLot> lots;
    public ParkingAttendant attendant;

    public ParkingManager(int noOfLots) {
        this(noOfLots,1);
    }

    public ParkingManager(int noOfLots, int lotCapacity) {
        lots = new ArrayList<>();
        for(int i=0; i<noOfLots;i++){
            lots.add(new ParkingLot(lotCapacity));
        }
        attendant = new ParkingAttendant();
    }

    public void addObserver(ParkingLotObeserver observer) {
        for (ParkingLot p: lots){
            p.addObserver(observer);
        }
    }

    public void park(Vehicle vehicle) {
        ParkingLot preferredLot=null;
        if(vehicle.type== Vehicle.Type.Truck){
            preferredLot= mostEmptyLot();
        }
        if(preferredLot==null){
            if(vehicle.isHandicapped){
                preferredLot=lots.get(0);
            }else{
                preferredLot = getPreferredLot();
            }
        }
        attendant.park(preferredLot, vehicle);
        isFull();
    }

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

    public Vehicle unPark(Vehicle vehicle) {
        for(ParkingLot p: lots){
            if(p.vehicles.values().contains(vehicle)){
                Vehicle vehicle1 = p.unPark(vehicle);
                isFull();
                return vehicle1;
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
    }

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

    public ParkingLot getParkedLot(Vehicle vehicle) {
        for(ParkingLot p: lots){
            if(p.vehicles.values().contains(vehicle)){
                return p;
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
    }

    public ArrayList<ParkingSpot> getCarLocationByColor(Vehicle.COLOR color) {
        ArrayList<ParkingSpot> locations = new ArrayList<>();
        for(ParkingLot p: lots){
            locations.addAll(p.getCarLocationByColor(color));
        }
        return locations;
    }

    public ArrayList<Vehicle> getCarByMakeAndColor(Vehicle.COLOR color, Vehicle.MAKE maker) {
        ArrayList<Vehicle> locations = new ArrayList<>();
        for(ParkingLot p: lots){
            locations.addAll(p.getCarLocationByMakeAndColor(color,maker));
        }
        return locations;
    }
}
