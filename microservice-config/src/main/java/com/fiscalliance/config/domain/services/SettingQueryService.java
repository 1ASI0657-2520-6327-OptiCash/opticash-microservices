package com.fiscalliance.config.domain.services;

import com.fiscalliance.config.domain.models.aggregates.Setting;
import com.fiscalliance.config.domain.models.queries.GetAllSettingsQuery;
import com.fiscalliance.config.domain.models.queries.GetSettingByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de dominio encargado de procesar consultas (queries)
 * relacionadas a las configuraciones (settings).
 */
public interface SettingQueryService {

    /**
     * Maneja la consulta para obtener un setting por ID.
     *
     * @param query query con el ID del setting
     * @return el setting, si existe
     */
    Optional<Setting> handle(GetSettingByIdQuery query);

    /**
     * Maneja la consulta para obtener todos los settings.
     *
     * @param query query sin parámetros
     * @return lista de configuraciones
     */
    List<Setting> handle(GetAllSettingsQuery query);
}
