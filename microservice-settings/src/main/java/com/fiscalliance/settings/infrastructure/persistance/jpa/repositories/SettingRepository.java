package com.fiscalliance.settings.infrastructure.persistance.jpa.repositories;

import com.fiscalliance.settings.domain.models.aggregates.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
}
