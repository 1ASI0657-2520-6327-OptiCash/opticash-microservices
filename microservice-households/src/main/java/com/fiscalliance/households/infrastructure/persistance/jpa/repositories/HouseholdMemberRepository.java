package com.fiscalliance.households.infrastructure.persistance.jpa.repositories;

import com.fiscalliance.households.domain.models.aggregates.Household;
import com.fiscalliance.households.domain.models.aggregates.HouseholdMember;
import com.fiscalliance.households.domain.models.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
    List<HouseholdMember> findAllByHouseholdId(Long householdId);
    boolean existsByHouseholdAndUserId(Household household, UserId userId);
}