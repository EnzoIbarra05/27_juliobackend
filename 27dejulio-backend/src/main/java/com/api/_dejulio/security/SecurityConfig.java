package com.api._dejulio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())      // Deshabilitar CSRF
            .cors(Customizer.withDefaults())   // Habilitar CORS
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()      // Permitir todas las solicitudes
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Dominio exacto del frontend
        config.setAllowedOrigins(List.of("https://a0041148.ferozo.com"));

        // Patrón compatible con navegadores modernos
        config.setAllowedOriginPatterns(List.of("https://a0041148.ferozo.com"));

        // Métodos permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Headers permitidos
        config.setAllowedHeaders(List.of("*"));

        // Permite credenciales (cookies, auth headers)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}

