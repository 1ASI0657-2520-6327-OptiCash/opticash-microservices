package com.fiscalliance.gateway.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    @Value("${jwt.secret}")
    private String secret;

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            System.out.println("➡️ Nueva petición: " + path);

            if (isPublicRoute(exchange)) {
                System.out.println("🟢 Ruta pública: " + path);
                return chain.filter(exchange);
            }

            System.out.println("🔒 Ruta protegida detectada: " + path);

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("❌ Falta el token o formato inválido en: " + path);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);
            System.out.println("🔐 Token recibido: " + token);

            try {
                SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                System.out.println("✅ Token válido para: " + username);
                System.out.println("🧩 Claims: " + claims);

                exchange.getRequest().mutate()
                        .header("X-User-Name", username)
                        .build();

                return chain.filter(exchange);

            } catch (Exception e) {
                System.out.println("❌ Error al validar token: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    private boolean isPublicRoute(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        boolean isPublic = path.startsWith("/api/v1/authentication")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger");

        if (isPublic)
            System.out.println("🔓 Ruta pública detectada: " + path);

        return isPublic;
    }
}
