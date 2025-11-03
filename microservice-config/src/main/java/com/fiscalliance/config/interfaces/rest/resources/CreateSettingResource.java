package com.fiscalliance.config.interfaces.rest.resources;

public record CreateSettingResource(
        Long userId,
        String language,
        Boolean darkMode,
        Boolean notificationsEnabled
) {}
