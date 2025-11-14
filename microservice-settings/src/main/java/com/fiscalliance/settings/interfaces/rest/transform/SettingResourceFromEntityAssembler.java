package com.fiscalliance.settings.interfaces.rest.transform;

import com.fiscalliance.settings.domain.models.aggregates.Setting;
import com.fiscalliance.settings.interfaces.rest.resources.SettingResource;

public class SettingResourceFromEntityAssembler {

    public static SettingResource toResourceFromEntity(Setting entity) {
        return new SettingResource(
                entity.getId(),
                entity.getUserId().userId(),
                entity.getLanguage() != null ? entity.getLanguage().code() : null,
                entity.getDarkMode() != null && entity.getDarkMode().isEnabled(),
                entity.getNotificationsEnabled() != null && entity.getNotificationsEnabled().isEnabled()
        );
    }
}
