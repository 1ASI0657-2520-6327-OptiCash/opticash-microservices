package com.fiscalliance.contributions.infrastructure.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class BillClient {

    private final RestTemplate restTemplate;
    private final String billsBaseUrl;

    public BillClient(RestTemplate restTemplate, @Value("${services.bills.url}") String billsBaseUrl) {
        this.restTemplate = restTemplate;
        this.billsBaseUrl = billsBaseUrl;
    }

    public BigDecimal getBillAmount(Long billId) {
        String url = billsBaseUrl + "/api/v1/bills/" + billId;
        ResponseEntity<BillResponse> response = restTemplate.getForEntity(url, BillResponse.class);
        return response.getBody().monto();
    }

    public record BillResponse(Long id, BigDecimal monto) {}
}
