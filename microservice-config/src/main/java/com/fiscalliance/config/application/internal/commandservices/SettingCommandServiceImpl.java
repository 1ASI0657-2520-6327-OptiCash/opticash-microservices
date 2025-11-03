package com.fiscalliance.config.application.internal.commandservices;

import com.fiscalliance.config.domain.models.aggregates.Setting;
import com.fiscalliance.config.domain.models.commands.CreateSettingCommand;
import com.fiscalliance.config.domain.services.SettingCommandService;
import com.fiscalliance.config.infrastructure.persistence.jpa.repositories.SettingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingCommandServiceImpl implements SettingCommandService {

    private final SettingRepository repository;

    public SettingCommandServiceImpl(SettingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Setting> handle(CreateSettingCommand command) {
        Setting setting = new Setting(command.userId(), command);
        repository.save(setting);
        return Optional.of(setting);
    }

    @Override
    public Optional<Setting> update(Long id, CreateSettingCommand command) {
        var existing = repository.findById(id);
        if (existing.isEmpty()) return Optional.empty();
        var setting = existing.get();
        setting.update(command);
        repository.save(setting);
        return Optional.of(setting);
    }

    @Override
    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
