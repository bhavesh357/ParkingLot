package com.bl;


import com.bl.exception.ParkingLotException;
import com.bl.model.Vehicle;
import com.bl.model.ParkingAttendant;
import com.bl.model.ParkingLotObeserver;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager {

    public List<ParkingLot> lots;
    public ParkingAttendant attendant;

    public ParkingManager(int noOfLots) {
        lots = new ArrayList<>();
        for(int i=0; i<noOfLots;i++ ){
            lots.add(new ParkingLot(1));
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
        int least=mostEmpty.vehicles.size();
        for(ParkingLot p: lots){
            if(p.vehicles.size()<least){
                least=p.vehicles.size();
                mostEmpty=p;
            }
        }
        return mostEmpty;
    }

    private ParkingLot getPreferredLot() {
        ParkingLot closestLot = lots.get(0);
        int max= closestLot.vehicles.size();
        boolean flag=true;
        for(int i=1;i<lots.size();i++){
            if (lots.get(i).vehicles.size() != max) {
                flag = false;
                break;
            }
        }
        if(flag && !closestLot.isFull()){
            return closestLot;
        }
        for(int i=1;i<lots.size();i++){
            if(lots.get(i).vehicles.size()<max){
                return lots.get(i);
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.ALL_LOTS_FULL);
    }

    public Vehicle unPark(Vehicle vehicle) {
        for(ParkingLot p: lots){
            if(p.vehicles.contains(vehicle)){
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

    public void setCapacity(int capacity) {
        for(ParkingLot p: lots){
            p.setCapacity(capacity);
        }
    }

    public ParkingLot getParkedLot(Vehicle vehicle) {
        for(ParkingLot p: lots){
            if(p.vehicles.contains(vehicle)){
                return p;
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
    }
}
