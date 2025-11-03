package com.fiscalliance.opticash.households.interfaces.rest.transform;



public class CreateHouseholdCommandFromResourceAssembler {

    public static com.example.spliteasybackend.households.domain.models.commands.CreateHouseholdCommand toCommandFromResource(CreateHouseholdResource resource) {
        return new CreateHouseholdCommand(
                resource.name(),
                resource.description(),
                resource.currency(),
                resource.representanteId()
        );
    }
}
