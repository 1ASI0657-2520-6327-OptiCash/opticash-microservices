package com.fiscalliance.households.interfaces.rest.transform;

import com.fiscalliance.households.domain.models.commands.CreateHouseholdMemberCommand;
import com.fiscalliance.households.interfaces.rest.resources.CreateHouseholdMemberResource;

public class CreateHouseholdMemberCommandFromResourceAssembler {
    public static CreateHouseholdMemberCommand toCommandFromResource(CreateHouseholdMemberResource resource) {
        return new CreateHouseholdMemberCommand(resource.householdId(), resource.userId());
    }
}