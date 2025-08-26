package com.nimbusnex.medicine_donation.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationFailedFieldResponses {
    private int id;
    private String field;
    private String message;
}
