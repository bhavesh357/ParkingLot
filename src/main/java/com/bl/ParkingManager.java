package com.bl;


import com.bl.exception.ParkingLotException;
import com.bl.model.Car;
import com.bl.model.ParkingAttendant;
import com.bl.model.ParkingLotObeserver;
import com.bl.model.ParkingLotOwner;

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

    public void park(Car car) {
        ParkingLot preferredLot = getPreferredLot();
        attendant.park(preferredLot,car);
        isFull();
    }

    private ParkingLot getPreferredLot() {
        ParkingLot closestLot = lots.get(0);
        int max= closestLot.cars.size();
        boolean flag=true;
        for(int i=1;i<lots.size();i++){
            if(flag && lots.get(i).cars.size()!=max){
                flag =false;
            }
        }
        if(flag && !closestLot.isFull()){
            return closestLot;
        }
        for(int i=1;i<lots.size();i++){
            if(lots.get(i).cars.size()<max){
                return lots.get(i);
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.ALL_LOTS_FULL);
    }

    public Car unPark(Car car) {
        for(ParkingLot p: lots){
            if(p.cars.contains(car)){
                Car car1 = p.unPark(car);
                isFull();
                return car1;
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
}
