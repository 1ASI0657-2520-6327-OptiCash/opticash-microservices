package com.fiscalliance.households.interfaces.rest.resources;

public record HouseholdMemberResource(
        Long id,
        Long userId,
        Long householdId
) {
}
