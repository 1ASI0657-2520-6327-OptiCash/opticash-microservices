package com.fiscalliance.config.interfaces.rest.resources;

public record SettingResource(
        Long id,
        Long userId,
        String language,
        Boolean darkMode,
        Boolean notificationsEnabled
) {}
