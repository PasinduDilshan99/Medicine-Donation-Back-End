package com.nimbusnex.medicine_donation.validation.impl;

import com.nimbusnex.medicine_donation.model.response.ValidationResponse;
import com.nimbusnex.medicine_donation.validation.CommonValidation;
import com.nimbusnex.medicine_donation.validation.RoleValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleValidationImpl implements RoleValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleValidationImpl.class);

    private final CommonValidation commonValidation;

    @Autowired
    public RoleValidationImpl(CommonValidation commonValidation) {
        this.commonValidation = commonValidation;
    }

    @Override
    public ValidationResponse validateRoleName(String name) {
        if (name == null || name.isEmpty()) {
            LOGGER.info("Role name is null or empty");
            return new ValidationResponse(false, new ArrayList<>());
        }
        boolean isValid = commonValidation.validateString(name);
        if (!isValid) {
            LOGGER.info("Role name validation failed");
            return new ValidationResponse(false, new ArrayList<>());
        }else {
            return new ValidationResponse(true, new ArrayList<>());
        }
    }
}
