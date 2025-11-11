package com.fiscalliance.households.application.internal.queryservices;

import com.fiscalliance.households.domain.models.aggregates.HouseholdMember;
import com.fiscalliance.households.domain.models.queries.GetAllHouseholdMembersQuery;
import com.fiscalliance.households.domain.models.queries.GetAllMembersByHouseholdIdQuery;
import com.fiscalliance.households.domain.models.queries.GetHouseholdMemberByIdQuery;
import com.fiscalliance.households.domain.services.HouseholdMemberQueryService;
import com.fiscalliance.households.infrastructure.persistance.jpa.repositories.HouseholdMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdMemberQueryServiceImpl implements HouseholdMemberQueryService {

    private final HouseholdMemberRepository repository;

    public HouseholdMemberQueryServiceImpl(HouseholdMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<HouseholdMember> handle(GetHouseholdMemberByIdQuery query) {
        return repository.findById(query.id());
    }

    @Override
    public List<HouseholdMember> handle(GetAllHouseholdMembersQuery query) {
        return repository.findAll();
    }

    @Override
    public List<HouseholdMember> handle(GetAllMembersByHouseholdIdQuery query) {
        return repository.findAllByHouseholdId(query.HouseholdId());
    }
}
