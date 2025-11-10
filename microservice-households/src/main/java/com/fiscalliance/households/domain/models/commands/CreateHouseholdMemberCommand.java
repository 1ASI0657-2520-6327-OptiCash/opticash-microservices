package com.fiscalliance.households.domain.models.commands;

public record CreateHouseholdMemberCommand(Long householdId, Long userId) {
    public CreateHouseholdMemberCommand {
        if (householdId == null || userId == null) {
            throw new IllegalArgumentException("householdId and userId cannot be null");
        }
    }
}
