package com.fiscalliance.config.application.acl;

import com.fiscalliance.config.domain.models.commands.CreateSettingCommand;
import com.fiscalliance.config.domain.models.queries.GetSettingByIdQuery;
import com.fiscalliance.config.domain.services.SettingCommandService;
import com.fiscalliance.config.domain.services.SettingQueryService;
import com.fiscalliance.config.interfaces.acl.SettingsContextFacade;
import org.springframework.stereotype.Service;

@Service
public class SettingsContextFacadeImpl implements SettingsContextFacade {

    private final SettingCommandService commandService;
    private final SettingQueryService queryService;

    public SettingsContextFacadeImpl(SettingCommandService commandService,
                                     SettingQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Override
    public Long createSetting(Long userId, String language, boolean darkMode, boolean notificationsEnabled) {
        var command = new CreateSettingCommand(userId, language, darkMode, notificationsEnabled);
        var setting = commandService.handle(command);
        return setting.map(s -> s.getId()).orElse(0L);
    }

    @Override
    public boolean existsSettingById(Long id) {
        var query = new GetSettingByIdQuery(id);
        return queryService.handle(query).isPresent();
    }
}
