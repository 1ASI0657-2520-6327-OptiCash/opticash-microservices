package com.fiscalliance.membercontributions.infrastructure.persistance.jpa.repositories;

import com.fiscalliance.membercontributions.domain.models.aggregates.MemberContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberContributionRepository extends JpaRepository<MemberContribution, Long> {
}
