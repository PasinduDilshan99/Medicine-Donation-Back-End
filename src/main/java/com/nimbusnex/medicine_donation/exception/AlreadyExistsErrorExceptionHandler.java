package com.nimbusnex.medicine_donation.exception;

public class AlreadyExistsErrorExceptionHandler extends RuntimeException{
    public AlreadyExistsErrorExceptionHandler(String message) {
        super(message);
    }
}
