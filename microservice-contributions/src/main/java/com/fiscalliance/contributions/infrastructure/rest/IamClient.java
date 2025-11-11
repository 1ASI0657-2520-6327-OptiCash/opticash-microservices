package com.fiscalliance.contributions.infrastructure.rest;
import com.fiscalliance.contributions.interfaces.rest.resources.UserIncomeResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Component
public class IamClient {

    private final RestTemplate restTemplate;
    private final String iamBaseUrl;

    public IamClient(RestTemplate restTemplate, @Value("${services.iam.url}") String iamBaseUrl) {
        this.restTemplate = restTemplate;
        this.iamBaseUrl = iamBaseUrl;
    }

    public List<UserIncomeResource> getUsersIncome(List<Long> userIds) {
        return userIds.stream()
                .map(this::getUserIncome)  // Llamada a /api/v1/users/{id}
                .toList();
    }

    private UserIncomeResource getUserIncome(Long userId) {
        String url = iamBaseUrl + "/api/v1/users/" + userId;
        var response = restTemplate.getForObject(url, UserResponse.class);
        if (response == null) {
            throw new RuntimeException("No se pudo obtener el usuario con id " + userId);
        }
        return new UserIncomeResource(response.id(), response.income());
    }

    // Record para mapear la respuesta del servicio IAM
    public record UserResponse(Long id, String username, String email, BigDecimal income, List<String> roles) {}

    // Record para devolver el dato que necesitas
    public record UserIncomeResource(Long userId, BigDecimal income) {}
}