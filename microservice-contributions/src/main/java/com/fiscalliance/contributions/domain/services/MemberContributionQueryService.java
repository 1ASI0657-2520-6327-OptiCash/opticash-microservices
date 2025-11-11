// MemberContributionQueryService.java
package com.fiscalliance.contributions.domain.services;


import com.fiscalliance.contributions.domain.models.aggregates.MemberContribution;
import com.fiscalliance.contributions.domain.models.queries.GetAllMemberContributionsQuery;
import com.fiscalliance.contributions.domain.models.queries.GetMemberContributionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MemberContributionQueryService {
    Optional<MemberContribution> handle(GetMemberContributionByIdQuery query);
    List<MemberContribution> handle(GetAllMemberContributionsQuery query);
}
