package com.nimbusnex.medicine_donation.validation;

import com.nimbusnex.medicine_donation.model.request.*;
import com.nimbusnex.medicine_donation.model.response.ValidationResponse;

public interface CommonValidation {
    ValidationResponse validateString(ValidateStringRequest validateStringRequest);
    ValidationResponse validateStringContainLettersNumbersOrUnderscore(ValidateStringRequest validateStringRequest);
    ValidationResponse validateStringLength(ValidateStringRequest validateStringRequest, Integer minValue, Integer maxValue);
    ValidationResponse validateStringContainCapitalLetters(ValidateStringRequest validateStringRequest);
    ValidationResponse validateIntegerNonEmpty(ValidateIntegerRequest validateIntegerRequest);
    ValidationResponse validateIntegerGreaterThanZero(ValidateIntegerRequest validateIntegerRequest);
    ValidationResponse validateLongNonEmpty(ValidateLongRequest validateLongRequest);
    ValidationResponse validateLongGreaterThanZero(ValidateLongRequest validateLongRequest);
    ValidationResponse validateLongContainOnlyNumbers(ValidateLongInStringFormatRequest validateLongInStringFormatRequest);
    ValidationResponse validateIntegerContainOnlyNumbers(ValidateIntegerInStringFormatRequest validateIntegerInStringFormatRequest);
}
