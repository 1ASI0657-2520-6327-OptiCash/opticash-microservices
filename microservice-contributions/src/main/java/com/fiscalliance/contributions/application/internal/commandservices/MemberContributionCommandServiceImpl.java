package com.fiscalliance.contributions.application.internal.commandservices;

import com.fiscalliance.contributions.domain.models.aggregates.MemberContribution;
import com.fiscalliance.contributions.domain.services.MemberContributionCommandService;
import com.fiscalliance.contributions.infrastructure.persistance.jpa.repositories.MemberContributionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberContributionCommandServiceImpl implements MemberContributionCommandService {
    private final MemberContributionRepository repository;

    public MemberContributionCommandServiceImpl(MemberContributionRepository repository) {
        this.repository = repository;
    }
    @Override
    public Optional<MemberContribution> handlMarkAsPaid(Long id) {
        var optional = repository.findById(id);
        if (optional.isEmpty()) return Optional.empty();

        var memberContribution = optional.get();
        memberContribution.markAsPaid();

        repository.save(memberContribution);
        return Optional.of(memberContribution);
    }
}
