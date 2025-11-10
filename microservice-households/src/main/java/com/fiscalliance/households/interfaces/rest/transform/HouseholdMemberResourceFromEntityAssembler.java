package com.fiscalliance.households.interfaces.rest.transform;


import com.fiscalliance.households.domain.models.aggregates.HouseholdMember;
import com.fiscalliance.households.interfaces.rest.resources.HouseholdMemberResource;

public class HouseholdMemberResourceFromEntityAssembler {

    public static HouseholdMemberResource toResourceFromEntity(HouseholdMember entity) {
        return new HouseholdMemberResource(
                entity.getId(),
                entity.getUserId()  != null ? entity.getUserId().userId() : null,
                entity.getHousehold() != null ? entity.getHousehold().getId() : null
        );
    }
}
