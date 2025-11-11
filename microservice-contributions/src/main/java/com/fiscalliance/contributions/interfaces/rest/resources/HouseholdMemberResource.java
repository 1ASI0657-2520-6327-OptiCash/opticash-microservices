package com.fiscalliance.contributions.interfaces.rest.resources;

public record HouseholdMemberResource(
        Long id,
        Long userId,
        Long householdId
) {}
