package com.fiscalliance.settings.domain.services;

import com.fiscalliance.settings.domain.models.aggregates.Setting;
import com.fiscalliance.settings.domain.models.commands.CreateSettingCommand;

import java.util.Optional;

public interface SettingCommandService {

    Optional<Setting> handle(CreateSettingCommand command);

    Optional<Setting> update(Long id, CreateSettingCommand command);

    boolean delete(Long id);
}
