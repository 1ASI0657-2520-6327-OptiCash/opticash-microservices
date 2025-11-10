package com.fiscalliance.households.domain.services;

import com.fiscalliance.households.domain.models.aggregates.HouseholdMember;
import com.fiscalliance.households.domain.models.queries.GetAllHouseholdMembersQuery;
import com.fiscalliance.households.domain.models.queries.GetHouseholdMemberByIdQuery;

import java.util.List;
import java.util.Optional;

public interface HouseholdMemberQueryService {
    Optional<HouseholdMember> handle(GetHouseholdMemberByIdQuery query);
    List<HouseholdMember> handle(GetAllHouseholdMembersQuery query);
}
