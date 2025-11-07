package com.fiscalliance.iam.interfaces.rest.transform;

import com.fiscalliance.iam.domain.model.aggregates.User;
import com.fiscalliance.iam.domain.model.entities.Role;
import com.fiscalliance.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), user.getEmail(), user.getIncome(),roles);
    }
}