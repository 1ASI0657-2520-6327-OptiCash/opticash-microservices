package com.fiscalliance.gateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
@Bean
public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .cors(cors -> cors.configurationSource(request -> {
            var corsConfig = new org.springframework.web.cors.CorsConfiguration();
            corsConfig.addAllowedOrigin("http://localhost:4200"); // tu Angular
            corsConfig.addAllowedMethod("*"); // GET, POST, PUT, DELETE...
            corsConfig.addAllowedHeader("*"); // Headers, incluido Authorization
            corsConfig.setAllowCredentials(true);
            return corsConfig;
        }))
        .authorizeExchange(exchanges -> exchanges.anyExchange().permitAll());

    return http.build();
}

}
