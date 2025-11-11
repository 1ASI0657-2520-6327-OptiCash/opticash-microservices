package com.fiscalliance.contributions.interfaces.rest.transform;


import com.fiscalliance.contributions.domain.models.aggregates.MemberContribution;
import com.fiscalliance.contributions.interfaces.rest.resources.MemberContributionResource;

public class MemberContributionResourceFromEntityAssembler {

    public static MemberContributionResource toResourceFromEntity(MemberContribution entity) {
        return new MemberContributionResource(
                entity.getId(),
                entity.getContributionId(),
                entity.getMemberId(),
                entity.getMonto(),
                entity.getStatus().name(),       // Convertir enum a String
                entity.getPagadoEn()
        );
    }
}
