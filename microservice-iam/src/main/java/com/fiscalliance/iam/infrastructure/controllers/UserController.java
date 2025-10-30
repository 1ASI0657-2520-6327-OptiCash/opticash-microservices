package com.fiscalliance.iam.infrastructure.controllers;

import com.fiscalliance.iam.domain.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public List<User> getAll() {
        return List.of(
                new User(1L, "ruben", "ruben@example.com"),
                new User(2L, "luigi", "luigi@example.com")
        );
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return new User(id, "user" + id, "user" + id + "@example.com");
    }
}