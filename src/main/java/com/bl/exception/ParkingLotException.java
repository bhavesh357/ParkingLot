package com.bl.exception;

public class ParkingLotException extends RuntimeException {
    public final ErrorType errorType;

    /**
     * constructor for exception
     * @param errorType //type of error
     */
    public ParkingLotException(ErrorType errorType) {
        this.errorType= errorType;
    }

    public enum ErrorType {
        CAR_ALREADY_PARKED,LOT_FULL, ALL_LOTS_FULL, CAR_NOT_PARKED
    }
}
