// MemberContributionRepository.java
package com.fiscalliance.contributions.infrastructure.persistance.jpa.repositories;


import com.fiscalliance.contributions.domain.models.aggregates.MemberContribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberContributionRepository extends JpaRepository<MemberContribution, Long> {
}
