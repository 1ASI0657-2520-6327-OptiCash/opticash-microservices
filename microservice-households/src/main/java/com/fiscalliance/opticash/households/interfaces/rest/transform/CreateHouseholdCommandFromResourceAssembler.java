package com.fiscalliance.opticash.households.interfaces.rest.transform;



public class CreateHouseholdCommandFromResourceAssembler {

    public static CreateHouseholdCommand toCommandFromResource(CreateHouseholdResource resource) {
        return new CreateHouseholdCommand(
                resource.name(),
                resource.description(),
                resource.currency(),
                resource.representanteId()
        );
    }
}
