package com.nimbusnex.medicine_donation.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {

    private Long id;

    private String name;

    private String description;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_by")
    private Long createdBy;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private Long updatedBy;
}