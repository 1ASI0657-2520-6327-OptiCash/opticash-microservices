package com.fiscalliance.config.interfaces.acl;

/**
 * Interfaz del Anti-Corruption Layer (ACL) para el contexto de Configuración.
 * Define las operaciones expuestas a otros microservicios o bounded contexts
 * (por ejemplo, IAM o Billing) para interactuar con las configuraciones
 * de usuario sin depender directamente del dominio interno.
 */
public interface SettingsContextFacade {

    /**
     * Crea una nueva configuración (setting) asociada a un usuario.
     *
     * @param userId ID del usuario al que pertenece la configuración
     * @param language Código de idioma (por ejemplo, "es", "en")
     * @param darkMode Si el modo oscuro está habilitado
     * @param notificationsEnabled Si las notificaciones están habilitadas
     * @return ID de la configuración creada
     */
    Long createSetting(Long userId, String language, boolean darkMode, boolean notificationsEnabled);

    /**
     * Verifica si existe una configuración por su ID.
     *
     * @param id ID de la configuración
     * @return true si existe, false en caso contrario
     */
    boolean existsSettingById(Long id);
}
