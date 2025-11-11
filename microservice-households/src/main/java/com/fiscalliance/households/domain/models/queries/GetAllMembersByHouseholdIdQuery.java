package com.fiscalliance.households.domain.models.queries;

public record GetAllMembersByHouseholdIdQuery(Long HouseholdId) {
    public GetAllMembersByHouseholdIdQuery {
        if (HouseholdId == null || HouseholdId <= 0) {
            throw new IllegalArgumentException("HouseholdId must be a positive, non-null value.");
        }
    }
}
