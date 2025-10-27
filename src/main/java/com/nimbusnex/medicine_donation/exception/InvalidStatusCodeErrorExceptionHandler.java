package com.nimbusnex.medicine_donation.exception;

public class InvalidStatusCodeErrorExceptionHandler extends RuntimeException{
    public InvalidStatusCodeErrorExceptionHandler(String message) {
        super(message);
    }
}
