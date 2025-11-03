package com.fiscalliance.config.domain.models.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value Object que representa si el modo oscuro está habilitado.
 */
@Embeddable
public class DarkMode {

    private boolean enabled;

    protected DarkMode() {}

    public DarkMode(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
