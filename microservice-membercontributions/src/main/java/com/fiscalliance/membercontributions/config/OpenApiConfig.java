package com.fiscalliance.membercontributions.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OpenApiConfig {

    @Bean
    @Primary // 👈 Fuerza a SpringDoc a usar este bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Microservicio de Contribución de Miembros")
                .version("1.0.0")
                .description("API pública sin autenticación para desarrollo"));
    }
}
