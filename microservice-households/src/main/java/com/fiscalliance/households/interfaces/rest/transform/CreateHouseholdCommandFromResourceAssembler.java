package com.fiscalliance.households.interfaces.rest.transform;

import com.fiscalliance.households.domain.models.commands.CreateHouseholdCommand;
import com.fiscalliance.households.interfaces.rest.resources.CreateHouseholdResource;

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
