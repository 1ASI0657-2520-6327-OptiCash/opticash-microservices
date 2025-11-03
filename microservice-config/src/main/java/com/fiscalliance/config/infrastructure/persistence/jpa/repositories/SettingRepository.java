package com.fiscalliance.config.infrastructure.persistence.jpa.repositories;

import com.fiscalliance.config.domain.models.aggregates.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
}
