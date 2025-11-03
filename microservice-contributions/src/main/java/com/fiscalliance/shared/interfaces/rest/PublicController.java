package com.fiscalliance.shared.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/public", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Public", description = "Health & readiness endpoints")
public class PublicController {
    @GetMapping("/ping")
    @Operation(summary = "Ping endpoint")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Service reachable")
    })
    public ResponseEntity<Map<String, Object>> ping() {
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "service", "msvc-contributions",
                "timestamp", Instant.now().toString()
        ));
    }

    @GetMapping("/ready")
    @Operation(summary = "Readiness endpoint")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Service ready")
    })
    public ResponseEntity<Map<String, Object>> ready() {
        return ResponseEntity.ok(Map.of(
                "ready", true,
                "service", "msvc-contributions",
                "timestamp", Instant.now().toString()
        ));
    }
}
