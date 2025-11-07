package com.fiscalliance.households.interfaces.rest.transform;

import com.fiscalliance.households.domain.models.aggregates.Household;
import com.fiscalliance.households.interfaces.rest.resources.HouseholdResource;

public class HouseholdResourceFromEntityAssembler {

    public static HouseholdResource toResourceFromEntity(Household entity) {
        return new HouseholdResource(
                entity.getId(),
                entity.getName() != null ? entity.getName().getValue() : null, // Convertir value object Name a String
                entity.getDescription(),
                entity.getCurrency(),
                entity.getRepresentante() != null ? entity.getRepresentante().userId() : null // Convertir value object Representante a Long
        );
    }
}

