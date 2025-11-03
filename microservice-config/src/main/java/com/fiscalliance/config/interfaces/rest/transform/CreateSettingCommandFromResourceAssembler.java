package com.fiscalliance.config.interfaces.rest.transform;

import com.fiscalliance.config.domain.models.commands.CreateSettingCommand;
import com.fiscalliance.config.interfaces.rest.resources.CreateSettingResource;

public class CreateSettingCommandFromResourceAssembler {
    public static CreateSettingCommand toCommandFromResource(CreateSettingResource resource) {
        return new CreateSettingCommand(
                resource.userId(),
                resource.language(),
                resource.darkMode(),
                resource.notificationsEnabled()
        );
    }
}
