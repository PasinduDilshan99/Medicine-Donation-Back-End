package com.nimbusnex.medicine_donation.exception;

public class UnAuthenticateErrorExceptionHandler extends RuntimeException{
    public UnAuthenticateErrorExceptionHandler(String message) {
        super(message);
    }
}
