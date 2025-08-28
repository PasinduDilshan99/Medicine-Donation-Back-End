package com.nimbusnex.medicine_donation.validation;

import com.nimbusnex.medicine_donation.model.response.ValidationResponse;

public interface RoleValidation {
    ValidationResponse validateRoleName(String name);
}
