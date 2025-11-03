package com.fiscalliance.config.domain.models.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value Object que representa si las notificaciones están habilitadas.
 */
@Embeddable
public class NotificationsEnabled {

    private boolean enabled;

    protected NotificationsEnabled() {}

    public NotificationsEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
