package com.fiscalliance.config.domain.models.queries;

/**
 * Query para obtener una configuración específica por su ID.
 */
public record GetSettingByIdQuery(Long id) {
    public GetSettingByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
    }
}
