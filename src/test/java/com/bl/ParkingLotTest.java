package com.bl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void givenObject_WhenParked_ShouldReturnTrue() {
        ParkingLot parkingLot = new ParkingLot();
        boolean park = parkingLot.park(new Object());
        Assert.assertTrue(park);
    }
}
