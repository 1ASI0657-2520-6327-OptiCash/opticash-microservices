package com.fiscalliance.iam.interfaces.rest.transform;

import com.fiscalliance.iam.domain.model.commands.SignInCommand;
import com.fiscalliance.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}