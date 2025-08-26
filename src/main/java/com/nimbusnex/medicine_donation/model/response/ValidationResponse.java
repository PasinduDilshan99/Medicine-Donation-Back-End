package com.nimbusnex.medicine_donation.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
    private boolean isValid;
    private List<ValidationFailedFieldResponses> validationFailedFieldResponses;
}
