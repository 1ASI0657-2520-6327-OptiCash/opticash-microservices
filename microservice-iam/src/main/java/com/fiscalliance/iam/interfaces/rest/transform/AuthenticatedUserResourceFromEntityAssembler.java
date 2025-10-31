package com.fiscalliance.iam.interfaces.rest.transform;

import com.fiscalliance.iam.domain.model.aggregates.User;
import com.fiscalliance.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}