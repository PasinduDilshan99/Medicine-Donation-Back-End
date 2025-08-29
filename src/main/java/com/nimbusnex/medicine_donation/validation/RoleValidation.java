package com.nimbusnex.medicine_donation.validation;

import com.nimbusnex.medicine_donation.model.entity.Role;
import com.nimbusnex.medicine_donation.model.request.ValidateStringRequest;
import com.nimbusnex.medicine_donation.model.response.ValidationResponse;

public interface RoleValidation {
    ValidationResponse validateRoleName(ValidateStringRequest roleName);

    ValidationResponse validateRoleId(Long id);
    ValidationResponse validateRoleDescription(ValidateStringRequest validateStringRequest);

    ValidationResponse validateRole(Role role);
}
