package com.nimbusnex.medicine_donation.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleRequest {
    private String roleName;
    private String roleDescription;
    private Long userId;
}
