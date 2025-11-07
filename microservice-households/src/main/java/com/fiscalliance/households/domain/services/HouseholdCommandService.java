package com.fiscalliance.households.domain.services;

import com.fiscalliance.households.domain.models.aggregates.Household;
import com.fiscalliance.households.domain.models.commands.CreateHouseholdCommand;

import java.util.Optional;

public interface HouseholdCommandService {
    Optional<Household> handle(CreateHouseholdCommand command);
    Optional<Household> update(Long id, CreateHouseholdCommand command);
    boolean delete(Long id);
}
