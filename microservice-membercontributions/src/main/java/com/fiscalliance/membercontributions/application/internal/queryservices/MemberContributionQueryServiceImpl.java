package com.fiscalliance.membercontributions.application.internal.queryservices;

import com.fiscalliance.membercontributions.domain.models.aggregates.MemberContribution;
import com.fiscalliance.membercontributions.domain.models.queries.GetAllMemberContributionsQuery;
import com.fiscalliance.membercontributions.domain.models.queries.GetMemberContributionByIdQuery;
import com.fiscalliance.membercontributions.domain.services.MemberContributionQueryService;
import com.fiscalliance.membercontributions.infrastructure.persistance.jpa.repositories.MemberContributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberContributionQueryServiceImpl implements MemberContributionQueryService {

    private final MemberContributionRepository memberContributionRepository;

    public MemberContributionQueryServiceImpl(MemberContributionRepository memberContributionRepository) {
        this.memberContributionRepository = memberContributionRepository;
    }

    @Override
    public List<MemberContribution> handle(GetAllMemberContributionsQuery query) {
        return memberContributionRepository.findAll();
    }

    @Override
    public Optional<MemberContribution> handle(GetMemberContributionByIdQuery query) {
        return memberContributionRepository.findById(query.id());
    }
}
