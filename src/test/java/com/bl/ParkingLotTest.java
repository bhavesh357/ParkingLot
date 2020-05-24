package com.bl;

import com.bl.exception.ParkingLotException;
import com.bl.model.AirportSecurity;
import com.bl.model.Car;
import com.bl.model.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void givenObject_WhenParked_ShouldReturnVoid() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
    }

    @Test
    public void givenObject_WhenSameCarParked_ShouldReturnException() {
        try {
            ParkingLot parkingLot = new ParkingLot(1);
            Car car = new Car();
            parkingLot.park(car);
            parkingLot.park(car);
        }catch (ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ErrorType.CAR_ALREADY_PARKED,e.errorType);
        }
    }

    @Test
    public void givenCar_WhenUnParked_ShouldReturnCar() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);
        Assert.assertEquals(car,parkingLot.unPark(car));
    }

    @Test
    public void givenCar_WhenUnParkedDifferentCar_ShouldReturnException() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);
        try{
            parkingLot.unPark(new Car());
        }catch (ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ErrorType.CAR_NOT_PARKED,e.errorType);
        }
    }

    @Test
    public void givenLimitCars_WhenFull_ShouldReturnTrue() {
        ParkingLot parkingLot = new ParkingLot(3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLot.addObserver(owner);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        Assert.assertTrue(owner.isCapacityFull());
    }

    @Test
    public void givenMoreThanLimitCars_WhenFull_ShouldReturnException() {
        ParkingLot parkingLot = new ParkingLot(3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLot.addObserver(owner);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        try{
            parkingLot.park(new Car());
        }catch (ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ErrorType.LOT_FULL,e.errorType);
        }
    }


    @Test
    public void givenLimitCars_WhenRedirectSecurity_ShouldReturnFalse() {
        ParkingLot parkingLot = new ParkingLot(3);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLot.addObserver(airportSecurity);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        Assert.assertTrue(airportSecurity.isCapacityFull());
    }

    @Test
    public void givenLimitCars_WhenNotFull_ShouldReturnFalse() {
        ParkingLot parkingLot = new ParkingLot(3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLot.addObserver(owner);
        Car car = new Car();
        parkingLot.park(car);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        Assert.assertTrue(owner.isCapacityFull());
        parkingLot.unPark(car);
        Assert.assertFalse(owner.isCapacityFull());
    }
}
