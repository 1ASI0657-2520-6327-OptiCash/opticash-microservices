package com.fiscalliance.bills.interfaces.rest.transform;

import com.fiscalliance.bills.domain.models.aggregates.Bill;
import com.fiscalliance.bills.interfaces.rest.resources.BillResource;

import java.math.BigDecimal;

public class BillResourceFromEntityAssembler {

    public static BillResource toResourceFromEntity(Bill entity) {
        return new BillResource(
                entity.getId(),
                entity.getHouseholdId().householdId(),
                entity.getDescription(),
                entity.getMonto() != null ? entity.getMonto().value() : BigDecimal.ZERO, /// Extraer BigDecimal desde el value object Money
                entity.getCreatedBy().userId(),
                entity.getFecha()
        );
    }
}
