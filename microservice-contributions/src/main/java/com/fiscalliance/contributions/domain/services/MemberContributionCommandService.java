package com.fiscalliance.contributions.domain.services;

import com.fiscalliance.contributions.domain.models.aggregates.MemberContribution;

import java.util.Optional;

public interface MemberContributionCommandService {
    Optional<MemberContribution> handlMarkAsPaid(Long id);
}
