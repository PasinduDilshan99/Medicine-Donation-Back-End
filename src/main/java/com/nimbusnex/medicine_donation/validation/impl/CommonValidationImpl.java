package com.nimbusnex.medicine_donation.validation.impl;

import com.nimbusnex.medicine_donation.validation.CommonValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommonValidationImpl implements CommonValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonValidationImpl.class);


    @Override
    public boolean validateString(String string) {
        if (string == null) {
            LOGGER.warn("Validation failed: string is null");
            return false;
        }
        if (string.trim().isEmpty()) {
            LOGGER.warn("Validation failed: string is empty or whitespace");
            return false;
        }

        return true;
    }

}
