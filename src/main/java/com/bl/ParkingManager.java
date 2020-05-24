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
        lots = new ArrayList<ParkingLot>();
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
    }

    private ParkingLot getPreferredLot() {
        for(int i=0;i<lots.size();i++){
            if(!lots.get(i).isFull()){
                return lots.get(i);
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.ALL_LOTS_FULL);
    }

    public Car unPark(Car car) {
        for(ParkingLot p: lots){
            if(p.cars.contains(car)){
                return p.unPark(car);
            }
        }
        throw new ParkingLotException(ParkingLotException.ErrorType.CAR_NOT_PARKED);
    }
}
