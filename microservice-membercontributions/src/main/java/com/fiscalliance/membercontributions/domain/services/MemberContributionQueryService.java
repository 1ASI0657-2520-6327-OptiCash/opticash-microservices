package com.fiscalliance.iam.domain.services;

import com.fiscalliance.iam.domain.models.aggregates.MemberContribution;
import com.fiscalliance.iam.domain.models.queries.GetAllMemberContributionsQuery;
import com.fiscalliance.iam.domain.models.queries.GetMemberContributionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MemberContributionQueryService {
    List<MemberContribution> handle(GetAllMemberContributionsQuery query);
    Optional<MemberContribution> handle(GetMemberContributionByIdQuery query);
}
