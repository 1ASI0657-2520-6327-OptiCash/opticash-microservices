package com.fiscalliance.membercontributions.interfaces.rest.transform;

import com.fiscalliance.membercontributions.domain.models.aggregates.MemberContribution;
import com.fiscalliance.membercontributions.interfaces.rest.resources.MemberContributionResource;

public class MemberContributionResourceFromEntityAssembler {

    private MemberContributionResourceFromEntityAssembler() {
        // Constructor privado para evitar instanciación
    }

    public static MemberContributionResource toResourceFromEntity(MemberContribution entity) {
        return new MemberContributionResource(
                entity.getId(),
                entity.getContributionId(),
                entity.getMemberId(),
                entity.getMonto(),
                entity.getStatus().name(),
                entity.getPagadoEn()
        );
    }
}
