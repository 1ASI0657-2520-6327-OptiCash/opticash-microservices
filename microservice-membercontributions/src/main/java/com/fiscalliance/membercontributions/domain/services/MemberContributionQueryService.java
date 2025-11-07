package com.fiscalliance.membercontributions.domain.services;

import com.fiscalliance.membercontributions.domain.models.aggregates.MemberContribution;
import com.fiscalliance.membercontributions.domain.models.queries.GetAllMemberContributionsQuery;
import com.fiscalliance.membercontributions.domain.models.queries.GetMemberContributionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MemberContributionQueryService {
    List<MemberContribution> handle(GetAllMemberContributionsQuery query);
    Optional<MemberContribution> handle(GetMemberContributionByIdQuery query);
}
