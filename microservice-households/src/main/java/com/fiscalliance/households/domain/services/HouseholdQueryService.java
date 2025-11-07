package com.fiscalliance.households.domain.services;

import com.fiscalliance.households.domain.models.aggregates.Household;
import com.fiscalliance.households.domain.models.queries.GetAllHouseholdsQuery;
import com.fiscalliance.households.domain.models.queries.GetHouseholdByIdQuery;

import java.util.List;
import java.util.Optional;

public interface HouseholdQueryService {
    Optional<Household> handle(GetHouseholdByIdQuery query);
    List<Household> handle(GetAllHouseholdsQuery query);
}
