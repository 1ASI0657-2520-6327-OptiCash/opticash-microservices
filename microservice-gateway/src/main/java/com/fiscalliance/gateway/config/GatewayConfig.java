package com.fiscalliance.gateway.config;

import com.fiscalliance.gateway.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        System.out.println("Bean JwtAuthenticationFilter registrado");
        return new JwtAuthenticationFilter();
    }
}