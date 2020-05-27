package com.bl;

import com.bl.exception.ParkingLotException;
import com.bl.model.AirportSecurity;
import com.bl.model.ParkingSpot;
import com.bl.model.Vehicle;
import com.bl.model.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    public void givenLimitCars_WhenFull_ShouldReturnTrue() {
        ParkingManager parkingManager = new ParkingManager(1,3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingManager.addObserver(owner);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Assert.assertTrue(owner.isCapacityFull());
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


    @Test
    public void givenLimitCars_WhenRedirectSecurity_ShouldReturnTrue() {
        ParkingManager parkingManager = new ParkingManager(1,3);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingManager.addObserver(airportSecurity);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Assert.assertTrue(airportSecurity.isCapacityFull());
    }

    @Test
    public void givenLimitCars_WhenNotFull_ShouldReturnFalse() {
        ParkingManager parkingManager = new ParkingManager(1,3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingManager.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parkingManager.park(vehicle);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Assert.assertTrue(owner.isCapacityFull());
        parkingManager.unPark(vehicle);
        Assert.assertFalse(owner.isCapacityFull());
    }

    @Test
    public void givenCar_WhenSpace_ShouldParkCar() {
        ParkingManager parking = new ParkingManager(1);
        ParkingLotOwner owner = new ParkingLotOwner();
        parking.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
    }

    @Test
    public void givenCar_WhengivenDifferentCarToGetLot_ShouldParkCar() {
        ParkingManager parking = new ParkingManager(1);
        ParkingLotOwner owner = new ParkingLotOwner();
        parking.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
        try {
            parking.getParkedLot(new Vehicle());
        }catch (ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ErrorType.CAR_NOT_PARKED,e.errorType);
        }
    }

    @Test
    public void givenCar_WhenFull_ShouldReturnException() {
        ParkingManager parking = new ParkingManager(1);
        ParkingLotOwner owner = new ParkingLotOwner();
        parking.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
        try {
            parking.park(new Vehicle());
        } catch (ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ErrorType.ALL_LOTS_FULL,e.errorType);
        }
    }

    @Test
    public void givenCar_WhenUnPark_ShouldReturnCar() {
        ParkingManager parking = new ParkingManager(2);
        ParkingLotOwner owner = new ParkingLotOwner();
        parking.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
        parking.park(new Vehicle());
        Vehicle vehicle1 = parking.unPark(vehicle);
        Assert.assertEquals(vehicle, vehicle1);
    }

    @Test
    public void givenCar_WhenUnParkDifferentCar_ShouldReturnException() {
        ParkingManager parking = new ParkingManager(2);
        ParkingLotOwner owner = new ParkingLotOwner();
        parking.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
        parking.park(new Vehicle());
        try {
            Vehicle vehicle1 = parking.unPark(new Vehicle());
        }catch (ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ErrorType.CAR_NOT_PARKED,e.errorType);
        }
    }

    @Test
    public void givenLimitCar_WhenFull_ShouldReturnTrue() {
        ParkingManager parking = new ParkingManager(2);
        ParkingLotOwner owner = new ParkingLotOwner();
        parking.addObserver(owner);
        AirportSecurity airportSecurity = new AirportSecurity();
        parking.addObserver(airportSecurity);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
        parking.park(new Vehicle());
        Assert.assertTrue(owner.isCapacityFull());
        Assert.assertTrue(airportSecurity.isCapacityFull());
    }

    @Test
    public void givenCar_WhenParkedForSomeTime_ShouldReturnTime() throws InterruptedException {
        ParkingManager parking = new ParkingManager(1);
        ParkingLotOwner owner = new ParkingLotOwner();
        parking.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
        Date time = Calendar.getInstance().getTime();
        Vehicle vehicle1 = parking.unPark(vehicle);
        Assert.assertEquals(time.toString(), vehicle1.getParkedTime().toString());
    }

    @Test
    public void givenCars_WhenNotFull_ShouldParkCarsEvenly() {
        ParkingManager parkingManager = new ParkingManager(3,3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingManager.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parkingManager.park(vehicle);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Vehicle vehicle4 = new Vehicle();
        parkingManager.park(vehicle4);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Assert.assertEquals(parkingManager.getParkedLot(vehicle),parkingManager.getParkedLot(vehicle4));
    }

    @Test
    public void givenCars_WheHandicappedDriver_ShouldParkCarsEvenly() {
        ParkingManager parkingManager = new ParkingManager(3,3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingManager.addObserver(owner);
        Vehicle vehicle = new Vehicle();
        parkingManager.park(vehicle);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Vehicle handicappedVehicle = new Vehicle();
        handicappedVehicle.setHandicappedDriver();
        parkingManager.park(handicappedVehicle);
        Assert.assertEquals(parkingManager.getParkedLot(vehicle),parkingManager.getParkedLot(handicappedVehicle));
    }

    @Test
    public void givenTruck_WhenParkedForSomeTime_ShouldReturnTime() throws InterruptedException {
        ParkingManager parkingManager = new ParkingManager(3,3);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Vehicle vehicle3 = new Vehicle();
        parkingManager.park(vehicle3);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Vehicle handicappedVehicle = new Vehicle();
        handicappedVehicle.setHandicappedDriver();
        parkingManager.park(handicappedVehicle);
        Vehicle truck = new Vehicle(Vehicle.Type.Truck);
        parkingManager.park(truck);
        Assert.assertEquals(parkingManager.getParkedLot(vehicle3),parkingManager.getParkedLot(truck));
    }

    @Test
    public void whenGivenCars_WhenWhite_ShouldReturnListOfCarLocation() {
        ParkingManager parkingManager = new ParkingManager(3,3);
        parkingManager.park(new Vehicle());
        parkingManager.park(new Vehicle());
        Vehicle whiteCar1=new Vehicle();
        whiteCar1.setColor(Vehicle.COLOR.WHITE);
        parkingManager.park(whiteCar1);
        parkingManager.park(new Vehicle());
        Vehicle whiteCar2=new Vehicle();
        whiteCar2.setColor(Vehicle.COLOR.WHITE);
        parkingManager.park(whiteCar2);
        PoliceStation policeStation = new PoliceStation();
        policeStation.addManager(parkingManager);
        ArrayList<ParkingSpot> locations = policeStation.getCar(Vehicle.COLOR.WHITE);
        ArrayList<ParkingSpot> carLocation = new ArrayList<>();
        carLocation.add(whiteCar2.getSpot());
        carLocation.add(whiteCar1.getSpot());
        Assert.assertEquals(carLocation,locations);
    }
}
