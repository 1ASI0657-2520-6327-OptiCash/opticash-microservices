package com.fiscalliance.households.application.internal.commandservices;

import com.fiscalliance.households.domain.models.aggregates.Household;
import com.fiscalliance.households.domain.models.aggregates.HouseholdMember;
import com.fiscalliance.households.domain.models.commands.CreateHouseholdMemberCommand;
import com.fiscalliance.households.domain.models.valueobjects.UserId;
import com.fiscalliance.households.domain.services.HouseholdMemberCommandService;
import com.fiscalliance.households.infrastructure.persistance.jpa.repositories.HouseholdMemberRepository;
import com.fiscalliance.households.infrastructure.persistance.jpa.repositories.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseholdMemberCommandServiceImpl implements HouseholdMemberCommandService {

    private final HouseholdRepository householdRepository;
    private final HouseholdMemberRepository memberRepository;

    public HouseholdMemberCommandServiceImpl(HouseholdRepository householdRepository,
                                             HouseholdMemberRepository memberRepository) {
        this.householdRepository = householdRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<HouseholdMember> handle(CreateHouseholdMemberCommand command) {
        Household household = householdRepository.findById(command.householdId())
                .orElseThrow(() -> new IllegalArgumentException("Household no encontrado"));

        UserId userId = new UserId(command.userId());

        boolean alreadyMember = memberRepository.existsByHouseholdAndUserId(household, userId);

        HouseholdMember member = HouseholdMember.create(household, userId, alreadyMember);
        memberRepository.save(member);

        return Optional.of(member);
    }
    @Override
    public Optional<HouseholdMember> update(Long id, CreateHouseholdMemberCommand command) {
        var optional = memberRepository.findById(id);
        if (optional.isEmpty()) return Optional.empty();

        var household = householdRepository.findById(command.householdId())
                .orElseThrow(() -> new IllegalArgumentException("Household no encontrado"));

        var userId = new UserId(command.userId());

        var member = optional.get();
        member.update(household, userId);
        memberRepository.save(member);
        return Optional.of(member);
    }

    @Override
    public boolean delete(Long id) {
        if (!memberRepository.existsById(id)) return false;
        memberRepository.deleteById(id);
        return true;
    }
}