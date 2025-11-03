package com.fiscalliance.iam.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record SignUpResource(
        String username,
        String email,
        String password,
        BigDecimal income,
        List<String> roles
) {}