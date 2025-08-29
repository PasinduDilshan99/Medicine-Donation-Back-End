package com.nimbusnex.medicine_donation.validation.impl;

import com.nimbusnex.medicine_donation.model.entity.Role;
import com.nimbusnex.medicine_donation.model.request.ValidateLongInStringFormatRequest;
import com.nimbusnex.medicine_donation.model.request.ValidateLongRequest;
import com.nimbusnex.medicine_donation.model.request.ValidateStringRequest;
import com.nimbusnex.medicine_donation.model.response.ValidationFailedFieldResponses;
import com.nimbusnex.medicine_donation.model.response.ValidationResponse;
import com.nimbusnex.medicine_donation.util.ValidationConstant;
import com.nimbusnex.medicine_donation.validation.CommonValidation;
import com.nimbusnex.medicine_donation.validation.RoleValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoleValidationImpl implements RoleValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleValidationImpl.class);

    private final CommonValidation commonValidation;

    @Autowired
    public RoleValidationImpl(CommonValidation commonValidation) {
        this.commonValidation = commonValidation;
    }

    @Override
    public ValidationResponse validateRoleName(ValidateStringRequest validateStringRequest) {
        ValidationResponse StringValidateResponse =  commonValidation.validateString(validateStringRequest);
        ValidationResponse nonAlphaNumeric = commonValidation.validateStringContainLettersNumbersOrUnderscore(validateStringRequest);
        ValidationResponse minMaxLength = commonValidation.validateStringLength(validateStringRequest, ValidationConstant.ROLE_NAME_MIN_LENGTH, ValidationConstant.ROLE_NAME_MAX_LENGTH);
        ValidationResponse containCapitalLetters = commonValidation.validateStringContainCapitalLetters(validateStringRequest);

        ValidationResponse validationResponse = new ValidationResponse();
        List<ValidationFailedFieldResponses> validationFailedFieldResponsesList = new ArrayList<>();
        validationResponse.setValid(true);
        validationResponse.setValidationFailedFieldResponses(validationFailedFieldResponsesList);

        if (!StringValidateResponse.isValid()) {
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(StringValidateResponse.getValidationFailedFieldResponses());
        }
        if (!nonAlphaNumeric.isValid()) {
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(nonAlphaNumeric.getValidationFailedFieldResponses());
        }
        if (!minMaxLength.isValid()) {
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(minMaxLength.getValidationFailedFieldResponses());
        }
        if (!containCapitalLetters.isValid()){
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(containCapitalLetters.getValidationFailedFieldResponses());
        }
        return validationResponse;
    }

    @Override
    public ValidationResponse validateRoleId(Long id) {
        ValidationResponse validateLongNonEmpty = commonValidation.validateLongNonEmpty(new ValidateLongRequest("Role id", id));
        ValidationResponse validateLongGreaterThanZero = commonValidation.validateLongGreaterThanZero(new ValidateLongRequest("Role id", id));
        ValidationResponse validateLongContainOnlyNumbers = commonValidation.validateLongContainOnlyNumbers(new ValidateLongInStringFormatRequest("Role id", String.valueOf(id)));

        ValidationResponse validationResponse = new ValidationResponse();
        List<ValidationFailedFieldResponses> validationFailedFieldResponsesList = new ArrayList<>();
        validationResponse.setValid(true);
        validationResponse.setValidationFailedFieldResponses(validationFailedFieldResponsesList);

        if (!validateLongNonEmpty.isValid()) {
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(validateLongNonEmpty.getValidationFailedFieldResponses());
        }
        if (!validateLongGreaterThanZero.isValid()) {
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(validateLongGreaterThanZero.getValidationFailedFieldResponses());
        }
        if(!validateLongContainOnlyNumbers.isValid()){
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(validateLongContainOnlyNumbers.getValidationFailedFieldResponses());
        }
        return validationResponse;
    }

    @Override
    public ValidationResponse validateRoleDescription(ValidateStringRequest validateStringRequest){
        ValidationResponse StringValidateResponse =  commonValidation.validateString(validateStringRequest);
        ValidationResponse validationResponse = new ValidationResponse();
        List<ValidationFailedFieldResponses> validationFailedFieldResponsesList = new ArrayList<>();
        validationResponse.setValid(true);
        validationResponse.setValidationFailedFieldResponses(validationFailedFieldResponsesList);

        if (!StringValidateResponse.isValid()) {
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(StringValidateResponse.getValidationFailedFieldResponses());
        }
        return validationResponse;

    }

    @Override
    public ValidationResponse validateRole(Role role) {
        ValidationResponse validateRoleName = validateRoleName(new ValidateStringRequest("Role name", role.getName()));
        ValidationResponse validateRoleDescription = validateRoleDescription(new ValidateStringRequest("Role description", role.getDescription()));
        ValidationResponse validationResponse = new ValidationResponse();
        List<ValidationFailedFieldResponses> validationFailedFieldResponsesList = new ArrayList<>();
        validationResponse.setValid(true);
        validationResponse.setValidationFailedFieldResponses(validationFailedFieldResponsesList);
        if (!validateRoleName.isValid()){
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(validateRoleName.getValidationFailedFieldResponses());
        }
        if (!validateRoleDescription.isValid()){
            validationResponse.setValid(false);
            validationFailedFieldResponsesList.addAll(validateRoleDescription.getValidationFailedFieldResponses());
        }
        return validationResponse;
    }
}
