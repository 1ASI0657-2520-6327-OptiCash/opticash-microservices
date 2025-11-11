package com.fiscalliance.contributions.infrastructure.rest;

import com.fiscalliance.contributions.interfaces.rest.resources.HouseholdMemberResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class HouseholdClient {

    private final RestTemplate restTemplate;
    private final String householdsBaseUrl;

    public HouseholdClient(RestTemplate restTemplate, @Value("${services.households.url}") String householdsBaseUrl) {
        this.restTemplate = restTemplate;
        this.householdsBaseUrl = householdsBaseUrl;
    }

    public List<HouseholdMemberResponse> getMembersByHousehold(Long householdId) {
        String url = householdsBaseUrl + "/api/v1/household-members/" + householdId + "/members";
        ResponseEntity<HouseholdMemberResponse[]> response = restTemplate.getForEntity(url, HouseholdMemberResponse[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}