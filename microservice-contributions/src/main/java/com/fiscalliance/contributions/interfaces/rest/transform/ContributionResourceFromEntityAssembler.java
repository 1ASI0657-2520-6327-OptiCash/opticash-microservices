package com.fiscalliance.contributions.interfaces.rest.transform;

import com.fiscalliance.contributions.domain.models.aggregates.Contribution;
import com.fiscalliance.contributions.interfaces.rest.resources.ContributionResource;

public class ContributionResourceFromEntityAssembler {
    public static ContributionResource toResourceFromEntity(Contribution e) {
        return new ContributionResource(
                e.getId(),
                e.getBillId(),
                e.getHouseholdId(),
                e.getDescription(),
                e.getStrategy().name(),
                e.getFechaLimite()
        );
    }
}
