package com.fiscalliance.config.domain.services;

import com.fiscalliance.config.domain.models.aggregates.Setting;
import com.fiscalliance.config.domain.models.commands.CreateSettingCommand;

import java.util.Optional;

/**
 * Servicio de dominio para manejar comandos de creación, actualización
 * y eliminación de configuraciones (settings).
 */
public interface SettingCommandService {

    /**
     * Maneja el comando para crear una configuración.
     *
     * @param command comando con los datos a registrar
     * @return configuración creada (si se logró)
     */
    Optional<Setting> handle(CreateSettingCommand command);

    /**
     * Actualiza una configuración existente.
     *
     * @param id ID del setting
     * @param command comando con nuevos valores
     * @return configuración actualizada, si existe
     */
    Optional<Setting> update(Long id, CreateSettingCommand command);

    /**
     * Elimina una configuración por ID.
     *
     * @param id ID del setting
     * @return true si se eliminó correctamente
     */
    boolean delete(Long id);
}
