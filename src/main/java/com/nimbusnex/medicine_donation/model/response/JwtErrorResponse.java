package com.nimbusnex.medicine_donation.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String jwtToken;
}
