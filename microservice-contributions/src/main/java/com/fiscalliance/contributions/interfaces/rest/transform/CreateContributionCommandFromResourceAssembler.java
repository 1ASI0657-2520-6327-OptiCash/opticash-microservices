package com.fiscalliance.contributions.interfaces.rest.transform;

import com.fiscalliance.contributions.domain.models.commands.CreateContributionCommand;
import com.fiscalliance.contributions.domain.models.valueobjects.Strategy;
import com.fiscalliance.contributions.interfaces.rest.resources.CreateContributionResource;

public class CreateContributionCommandFromResourceAssembler {

    public static CreateContributionCommand toCommandFromResource(CreateContributionResource resource) {
        return new CreateContributionCommand(
                resource.billId(),
                resource.householdId(),
                resource.description(),
                resource.fechaLimite(),
                Strategy.valueOf(resource.strategy().toUpperCase())
        );
    }
}
