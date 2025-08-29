package com.nimbusnex.medicine_donation.validation.impl;

import com.nimbusnex.medicine_donation.model.enums.RoleStatus;
import com.nimbusnex.medicine_donation.model.request.*;
import com.nimbusnex.medicine_donation.model.response.ValidationFailedFieldResponses;
import com.nimbusnex.medicine_donation.model.response.ValidationResponse;
import com.nimbusnex.medicine_donation.util.RegexPatterns;
import com.nimbusnex.medicine_donation.util.ValidationEnumsNames;
import com.nimbusnex.medicine_donation.validation.CommonValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CommonValidationImpl implements CommonValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonValidationImpl.class);


    @Override
    public ValidationResponse validateString(ValidateStringRequest validateStringRequest) {
        return validateStringNonEmpty(validateStringRequest);
    }

    private ValidationResponse validateStringNonEmpty(ValidateStringRequest validateStringRequest) {
        if (validateStringRequest.getValue() == null || validateStringRequest.getValue().isEmpty()) {
            LOGGER.info("{} is null or empty", validateStringRequest.getKey());
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(
                            1,
                            validateStringRequest.getKey(),
                            validateStringRequest.getKey() + " is null or empty."
                    )
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateStringContainLettersNumbersOrUnderscore(ValidateStringRequest validateStringRequest) {
        if (!validateStringRequest.getValue().matches(RegexPatterns.CONTAIN_ONLY_ALPHABETS_AND_NUMBERS_AND_UNDERSCORE)) {
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(1, validateStringRequest.getKey(), validateStringRequest.getKey() + " contains invalid characters.")
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateStringLength(ValidateStringRequest validateStringRequest, Integer minValue, Integer maxValue) {
        if (validateStringRequest.getValue().length() < minValue || validateStringRequest.getValue().length() > maxValue) {
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(1, validateStringRequest.getKey(), validateStringRequest.getKey() + " must be between 3 and 50 characters.")
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateStringContainCapitalLetters(ValidateStringRequest validateStringRequest) {
        if (!validateStringRequest.getValue().matches("^(?=.*[A-Z])[A-Za-z0-9_]+$")) {
            LOGGER.info("{} must contain at least one capital letter and only valid characters", validateStringRequest.getKey());
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(
                            2,
                            validateStringRequest.getKey(),
                            validateStringRequest.getKey() + " must contain at least one uppercase letter (A-Z) and only letters, digits, or underscores."
                    )
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateIntegerNonEmpty(ValidateIntegerRequest validateIntegerRequest) {
        if (validateIntegerRequest.getValue() == null) {
            LOGGER.info("{} is null", validateIntegerRequest.getKey());
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(
                            1,
                            validateIntegerRequest.getKey(),
                            validateIntegerRequest.getKey() + " is null."
                    )
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateIntegerGreaterThanZero(ValidateIntegerRequest validateIntegerRequest) {
        if (validateIntegerRequest.getValue() <= 0) {
            LOGGER.info("{} is less than or equal to zero", validateIntegerRequest.getKey());
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(
                            1,
                            validateIntegerRequest.getKey(),
                            validateIntegerRequest.getKey() + " must be greater than zero."
                    )
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateLongNonEmpty(ValidateLongRequest validateLongRequest) {
        if (validateLongRequest.getValue() == null) {
            LOGGER.info("{} is null", validateLongRequest.getKey());
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(
                            1,
                            validateLongRequest.getKey(),
                            validateLongRequest.getKey() + " is null."
                    )
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateLongGreaterThanZero(ValidateLongRequest validateLongRequest) {
        if (validateLongRequest.getValue() <= 0) {
            LOGGER.info("{} is less than or equal to zero", validateLongRequest.getKey());
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(
                            1,
                            validateLongRequest.getKey(),
                            validateLongRequest.getKey() + " must be greater than zero."
                    )
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateLongContainOnlyNumbers(ValidateLongInStringFormatRequest validateLongInStringFormatRequest) {
        if (!validateLongInStringFormatRequest.getValue().matches("\\d+")) {
            LOGGER.info("{} contains non-numeric characters", validateLongInStringFormatRequest.getKey());
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(
                            2,
                            validateLongInStringFormatRequest.getKey(),
                            validateLongInStringFormatRequest.getKey() + " must contain only numeric digits."
                    )
            ));
        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateIntegerContainOnlyNumbers(ValidateIntegerInStringFormatRequest validateIntegerInStringFormatRequest) {
        if (!validateIntegerInStringFormatRequest.getValue().matches("\\d+")) {
            LOGGER.info("{} contains non-numeric characters", validateIntegerInStringFormatRequest.getKey());
            return new ValidationResponse(false, Collections.singletonList(
                    new ValidationFailedFieldResponses(
                            2,
                            validateIntegerInStringFormatRequest.getKey(),
                            validateIntegerInStringFormatRequest.getKey() + " must contain only numeric digits."
                    )
            ));

        } else {
            return new ValidationResponse(true, Collections.emptyList());
        }
    }

    @Override
    public ValidationResponse validateStatus(ValidateStatusRequest validateStatusRequest) {
        ValidationResponse validateNull = validateStringNonEmpty(new ValidateStringRequest(validateStatusRequest.getKey(), validateStatusRequest.getValue()));
        if (!validateNull.isValid()) {
            return validateNull;
        } else {
            String value = validateStatusRequest.getValue();
            // ROLE ENUM
            if (validateStatusRequest.getKey().equals(ValidationEnumsNames.ROLE_STATUS)) {
                boolean isValidStatus = false;
                for (RoleStatus status : RoleStatus.values()) {
                    if (status.name().equalsIgnoreCase(value)) {
                        isValidStatus = true;
                        break;
                    }
                }
                if (!isValidStatus) {
                    return new ValidationResponse(false,
                            Collections.singletonList(
                                    new ValidationFailedFieldResponses(
                                            1,
                                            validateStatusRequest.getKey(),
                                            validateStatusRequest.getKey() + " is not a valid RoleStatus."
                                    )
                            )
                    );
                }
            }

        }
        return new ValidationResponse(true, Collections.emptyList());
    }
}
