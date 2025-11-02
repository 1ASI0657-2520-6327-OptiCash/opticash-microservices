package com.fiscalliance.bills.domain.models.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable
public record HouseholdId(Long householdId) {
    public HouseholdId {
        if (householdId == null || householdId <= 0) {
            throw new IllegalArgumentException("HouseholdId must be a positive non-null value.");
        }
    }
}
