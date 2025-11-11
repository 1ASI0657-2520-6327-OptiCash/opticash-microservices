package com.fiscalliance.contributions.domain.models.valueobjects;


import java.math.BigDecimal;

public record UserIncome(Long userId, BigDecimal income) {}
