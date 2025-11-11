package com.fiscalliance.contributions.domain.services;

import com.fiscalliance.contributions.domain.models.aggregates.Contribution;
import com.fiscalliance.contributions.domain.models.queries.GetContributionByIdQuery;
import com.fiscalliance.contributions.domain.models.queries.GetAllContributionsQuery;

import java.util.List;
import java.util.Optional;

public interface ContributionQueryService {
    Optional<Contribution> handle(GetContributionByIdQuery query);
    List<Contribution> handle(GetAllContributionsQuery query);
}
