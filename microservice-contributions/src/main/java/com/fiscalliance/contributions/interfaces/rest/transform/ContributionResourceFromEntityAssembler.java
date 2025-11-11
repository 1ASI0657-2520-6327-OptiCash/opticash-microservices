package com.fiscalliance.contributions.interfaces.rest.transform;

import com.fiscalliance.contributions.domain.models.aggregates.Contribution;
import com.fiscalliance.contributions.interfaces.rest.resources.ContributionResource;

public class ContributionResourceFromEntityAssembler {

    public static ContributionResource toResourceFromEntity(Contribution entity) {
        return new ContributionResource(
                entity.getId(),
                entity.getBillId().billId(),
                entity.getHouseholdId().householdId(),
                entity.getDescription(),
                entity.getStrategy().name(),
                entity.getFechaLimite()
        );
    }
}
