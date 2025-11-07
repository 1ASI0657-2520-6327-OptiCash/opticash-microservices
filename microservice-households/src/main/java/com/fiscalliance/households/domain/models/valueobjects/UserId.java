package com.fiscalliance.households.domain.models.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long userId) {
    public UserId{
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("El userId debe ser un valor positivo y no nulo");
        }
    }


}
