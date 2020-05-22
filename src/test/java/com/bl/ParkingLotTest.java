package com.bl;

import com.bl.model.Car;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void givenObject_WhenParked_ShouldReturnTrue() {
        ParkingLot parkingLot = new ParkingLot();
        boolean park = parkingLot.park(new Car());
        Assert.assertTrue(park);
    }

    @Test
    public void givenCar_WhenUnparked_ShouldReturnCar() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        Assert.assertEquals(car,parkingLot.unpark());
    }
}
