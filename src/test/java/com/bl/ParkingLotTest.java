package com.bl;

import com.bl.model.AiportSecurity;
import com.bl.model.Car;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingLotTest {
    @Mock
    AiportSecurity aiportSecurity;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void givenObject_WhenParked_ShouldReturnToken() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
    }

    @Test
    public void givenCar_WhenUnparked_ShouldReturnCar() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);
        Assert.assertEquals(car,parkingLot.unPark(car));
    }

    @Test
    public void givenLimitCars_WhenFull_ShoulReturnTrue() {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.isFull();
        Assert.assertTrue(parkingLot.isSignUp());
    }

    @Test
    public void givenLimitCars_WhenRedirectSecurity_ShouldReturnFalse() {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        aiportSecurity = mock(AiportSecurity.class);
        when(aiportSecurity.redirectStaff(true)).thenReturn(true);
        Assert.assertTrue(parkingLot.redirectStaff(aiportSecurity));
    }

    @Test
    public void givenLimitCars_WhenNotFull_ShouldReturnFalse() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car();
        parkingLot.park(car);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.isFull();
        parkingLot.unPark(car);
        Assert.assertTrue(parkingLot.isSignUp());
    }
}
