package com.fiscalliance.iam.interfaces.rest.transform;

import com.fiscalliance.iam.domain.models.commands.CreateMemberContributionCommand;
import com.fiscalliance.iam.domain.models.valueobjects.Status;
import com.fiscalliance.iam.interfaces.rest.resources.CreateMemberContributionResource;

public class CreateMemberContributionCommandFromResourceAssembler {

    private CreateMemberContributionCommandFromResourceAssembler() {
        // Constructor privado para evitar instanciación
    }

    public static CreateMemberContributionCommand toCommandFromResource(CreateMemberContributionResource resource) {
        return new CreateMemberContributionCommand(
                resource.contributionId(),
                resource.memberId(),
                resource.monto(),
                Status.valueOf(resource.status().toUpperCase()),
                resource.pagadoEn()
        );
    }
}
