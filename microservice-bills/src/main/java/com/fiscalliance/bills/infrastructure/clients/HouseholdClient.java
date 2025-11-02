package com.fiscalliance.bills.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "households-service", url = "${services.households.url}")
public interface HouseholdClient {
    @GetMapping("/api/v1/households/{id}/exists")
    boolean householdExists(@PathVariable Long id);
}
