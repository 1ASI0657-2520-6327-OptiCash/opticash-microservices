package com.fiscalliance.contributions.interfaces.rest.transform;

import com.fiscalliance.contributions.domain.models.aggregates.Contribution;
import com.fiscalliance.contributions.interfaces.rest.resources.ContributionResource;

public class ContributionResourceFromEntityAssembler {

    public static ContributionResource toResourceFromEntity(Contribution entity) {
        return new ContributionResource(
                entity.getId(),
                entity.getBill() != null ? entity.getBill().getId() : null,
                entity.getHousehold() != null ? entity.getHousehold().getId() : null,
                entity.getDescription(),
                entity.getStrategy().name(),       // Enum a String
                entity.getFechaLimite()
        );
    }
}
