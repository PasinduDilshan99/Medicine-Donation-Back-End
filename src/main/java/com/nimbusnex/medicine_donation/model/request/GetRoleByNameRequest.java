package com.nimbusnex.medicine_donation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleByNameRequest {
    @JsonProperty("role_name")
    private String roleName;
}
