package com.fiscalliance.contributions.domain.models.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record HouseholdId(Long householdId) {

    public HouseholdId {
        if (householdId == null || householdId <= 0) {
            throw new IllegalArgumentException("El householdId debe ser un valor positivo.");
        }
    }
}
