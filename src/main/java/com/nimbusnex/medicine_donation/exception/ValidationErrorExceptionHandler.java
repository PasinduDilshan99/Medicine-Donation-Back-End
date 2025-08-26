package com.nimbusnex.medicine_donation.exception;

import com.nimbusnex.medicine_donation.model.response.ValidationFailedFieldResponses;

import java.util.List;

public class ValidationErrorExceptionHandler extends RuntimeException{

    private final List<ValidationFailedFieldResponses> validationFailedFieldResponsesList;

    public ValidationErrorExceptionHandler(String message, List<ValidationFailedFieldResponses> validationFailedFieldResponsesList) {
        super(message);
        this.validationFailedFieldResponsesList = validationFailedFieldResponsesList;
    }

    public List<ValidationFailedFieldResponses> getValidationFailedFieldResponsesList() {
        return validationFailedFieldResponsesList;
    }
}
