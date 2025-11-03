package com.fiscalliance.config.application.internal.queryservices;

import com.fiscalliance.config.domain.models.aggregates.Setting;
import com.fiscalliance.config.domain.models.queries.GetAllSettingsQuery;
import com.fiscalliance.config.domain.models.queries.GetSettingByIdQuery;
import com.fiscalliance.config.domain.services.SettingQueryService;
import com.fiscalliance.config.infrastructure.persistence.jpa.repositories.SettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingQueryServiceImpl implements SettingQueryService {

    private final SettingRepository repository;

    public SettingQueryServiceImpl(SettingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Setting> handle(GetSettingByIdQuery query) {
        return repository.findById(query.id());
    }

    @Override
    public List<Setting> handle(GetAllSettingsQuery query) {
        return repository.findAll();
    }
}
