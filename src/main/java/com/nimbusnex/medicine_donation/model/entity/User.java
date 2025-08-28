package com.nimbusnex.medicine_donation.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("full_name")
    private String fullName;

    private String nic;

    private String email;

    @JsonIgnore
    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("user_type_id")
    private Long userTypeId;

    @JsonProperty("status_id")
    private Long statusId;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @Builder.Default
    @JsonProperty("email_verified")
    private Boolean emailVerified = false;

    @Builder.Default
    @JsonProperty("phone_verified")
    private Boolean phoneVerified = false;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    @JsonProperty("wallet_id")
    private Long walletId;

    // New fields for roles and privileges
    @JsonIgnore
    private List<String> roles;

    @JsonIgnore
    private List<String> privileges;
}