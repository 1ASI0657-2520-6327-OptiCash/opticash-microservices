package com.fiscalliance.opticash.households.interfaces.rest.transform;



public class HouseholdResourceFromEntityAssembler {

    public static HouseholdResource toResourceFromEntity(Household entity) {
        return new HouseholdResource(
                entity.getId(),
                entity.getName() != null ? entity.getName().getValue() : null, // Convertir value object Name a String
                entity.getDescription(),
                entity.getCurrency(),
                entity.getRepresentante() != null ? entity.getRepresentante().getId() : null // ✅ Solución
        );
    }
}

