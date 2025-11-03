package com.fiscalliance.contributions.domain.services;

import com.fiscalliance.contributions.domain.models.aggregates.Contribution;
import com.fiscalliance.contributions.domain.models.commands.CreateContributionCommand;

import java.util.Optional;

public interface ContributionCommandService {
    Optional<Contribution> handle(CreateContributionCommand command);
    Optional<Contribution> update(Long id, CreateContributionCommand command);
    boolean delete(Long id);
}
