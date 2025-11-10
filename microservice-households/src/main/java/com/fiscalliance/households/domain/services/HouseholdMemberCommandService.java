package com.fiscalliance.households.domain.services;

import com.fiscalliance.households.domain.models.aggregates.HouseholdMember;
import com.fiscalliance.households.domain.models.commands.CreateHouseholdMemberCommand;

import java.util.Optional;

public interface HouseholdMemberCommandService {
    Optional<HouseholdMember> handle(CreateHouseholdMemberCommand command);
    Optional<HouseholdMember> update(Long id, CreateHouseholdMemberCommand command);
    boolean delete(Long id);
}