package com.fiscalliance.contributions.domain.models.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable
public record BillId(Long billId) {

    public BillId {
        if (billId == null || billId <= 0) {
            throw new IllegalArgumentException("El billId debe ser un valor positivo.");
        }
    }
}
