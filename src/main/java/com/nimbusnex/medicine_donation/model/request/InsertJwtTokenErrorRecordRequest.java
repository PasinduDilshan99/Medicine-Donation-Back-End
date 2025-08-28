package com.nimbusnex.medicine_donation.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertJwtTokenErrorRecordRequest {
    private int status;
    private String code;
    private String message;
    private String timestamp;
    private String errorMessage;
    private String path;
    private String userId;
    private String jwtToken;
}
