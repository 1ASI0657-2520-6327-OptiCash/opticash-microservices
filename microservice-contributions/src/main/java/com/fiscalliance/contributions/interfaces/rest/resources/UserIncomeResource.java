package com.fiscalliance.contributions.interfaces.rest.resources;

import java.math.BigDecimal;

public record UserIncomeResource(
        Long userId,
        BigDecimal income
) {}
