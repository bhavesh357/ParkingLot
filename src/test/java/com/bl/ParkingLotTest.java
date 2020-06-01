package com.bl;

import com.bl.exception.ParkingLotException;
import com.bl.model.AirportSecurity;
import com.bl.model.ParkingLotOwner;
import com.bl.model.ParkingSpot;
import com.bl.model.Vehicle;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.bl.model.Vehicle.MAKE.BMW;
import static com.bl.model.Vehicle.MAKE.TOYOTA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingLotTest {
     @Test
        public void givenObject_WhenParked_ShouldReturnVoid() {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.park(new Vehicle());
        }

        @Test
        public void givenObject_WhenSameCarParked_ShouldReturnException() {
            try {
                ParkingLot parkingLot = new ParkingLot(1);
                Vehicle vehicle = new Vehicle();
                parkingLot.park(vehicle);
                parkingLot.park(vehicle);
            }catch (ParkingLotException e){
                Assert.assertEquals(ParkingLotException.ErrorType.CAR_ALREADY_PARKED,e.errorType);
            }
        }

        @Test
        public void givenCar_WhenUnParked_ShouldReturnCar() {
            ParkingLot parkingLot = new ParkingLot(1);
            Vehicle vehicle = new Vehicle();
            parkingLot.park(vehicle);
            Assert.assertEquals(vehicle,parkingLot.unPark(vehicle));
        }

        @Test
        public void givenCar_WhenUnParkedDifferentCar_ShouldReturnException() {
            ParkingLot parkingLot = new ParkingLot(1);
            Vehicle vehicle = new Vehicle();
            parkingLot.park(vehicle);
            try{
                parkingLot.unPark(new Vehicle());
            }catch (ParkingLotException e){
                Assert.assertEquals(ParkingLotException.ErrorType.CAR_NOT_PARKED,e.errorType);
            }
        }

        @Test
        public void givenMoreThanLimitCars_WhenFull_ShouldReturnException() {
            ParkingLot parkingLot = new ParkingLot(3);
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLot.addObserver(owner);
            parkingLot.park(new Vehicle());
            parkingLot.park(new Vehicle());
            parkingLot.park(new Vehicle());
            try{
                parkingLot.park(new Vehicle());
            }catch (ParkingLotException e){
                Assert.assertEquals(ParkingLotException.ErrorType.LOT_FULL,e.errorType);
            }
        }
}
