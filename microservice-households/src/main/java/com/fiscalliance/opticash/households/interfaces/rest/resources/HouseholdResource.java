package com.fiscalliance.opticash.households.interfaces.rest.resources;

public record HouseholdResource(
        Long id,
        String name,
        String description,
        String currency,
        Long representanteId
) {
}
