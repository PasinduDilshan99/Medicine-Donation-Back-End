package com.nimbusnex.medicine_donation.model.enums;

import lombok.Getter;

@Getter
public enum RoleStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    PENDING("Pending approval"),
    SUSPENDED("Suspended"),
    ARCHIVED("Archived"),
    EXPIRED("Expired");

    private final String description;

    RoleStatus(String description){
        this.description = description;
    }
}
