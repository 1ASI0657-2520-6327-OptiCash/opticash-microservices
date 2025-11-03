package com.fiscalliance.config.interfaces.rest.transform;

import com.fiscalliance.config.domain.models.aggregates.Setting;
import com.fiscalliance.config.interfaces.rest.resources.SettingResource;

public class SettingResourceFromEntityAssembler {
    public static SettingResource toResourceFromEntity(Setting entity) {
        return new SettingResource(
                entity.getId(),
                entity.getUserId(),
                entity.getLanguage().code(),
                entity.getDarkMode().isEnabled(),
                entity.getNotificationsEnabled().isEnabled()
        );
    }
}
