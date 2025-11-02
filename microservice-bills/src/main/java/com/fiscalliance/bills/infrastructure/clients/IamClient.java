package com.fiscalliance.bills.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "iam-service", url = "${services.iam.url}")
public interface IamClient {
    @GetMapping("/api/v1/users/{id}/exists")
    boolean userExists(@PathVariable Long id);
}