package com.fiscalliance.contributions.interfaces.rest.resources;

import java.time.LocalDate;

public record ContributionResource(
        Long id,
        Long billId,
        Long HouseholdId,
        String description,
        String strategy,
        LocalDate fechaLimite
) {
}
