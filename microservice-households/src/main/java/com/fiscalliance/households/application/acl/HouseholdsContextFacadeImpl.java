package com.fiscalliance.households.application.acl;

import com.fiscalliance.households.domain.models.commands.CreateHouseholdCommand;
import com.fiscalliance.households.domain.models.queries.GetHouseholdByIdQuery;
import com.fiscalliance.households.domain.services.HouseholdCommandService;
import com.fiscalliance.households.domain.services.HouseholdQueryService;
import com.fiscalliance.households.interfaces.acl.HouseholdsContextFacade;
import org.springframework.stereotype.Service;

@Service
public class HouseholdsContextFacadeImpl implements HouseholdsContextFacade {

    private final HouseholdCommandService commandService;
    private final HouseholdQueryService queryService;

    public HouseholdsContextFacadeImpl(HouseholdCommandService commandService, HouseholdQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Override
    public Long createHousehold(String name, String description, String currency, Long representanteId) {
        var command = new CreateHouseholdCommand(
                name,
                description,
                currency,
                representanteId
        );
        var household = commandService.handle(command);
        return household.map(h -> h.getId()).orElse(0L);
    }

    @Override
    public boolean existsHouseholdById(Long id) {
        var query = new GetHouseholdByIdQuery(id);
        return queryService.handle(query).isPresent();
    }
}
