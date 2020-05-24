package com.bl.exception;

public class ParkingLotException extends RuntimeException {
    private final ErrorType errorType;

    public ParkingLotException(ErrorType errorType) {
        this.errorType= errorType;
    }

    public enum ErrorType {
        CAR_ALREADYPARKED,LOT_FULL, CAR_NOT_PARKED;
    }
}
