package com.example.AcademiFlow.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

/**
 * Custom CORS configuration class.
 */
@Component
public class CustomCorsConfiguration implements CorsConfigurationSource {

    /**
     * Provides a {@link CorsConfiguration} based on the incoming request.
     *
     * @param request the HTTP request
     * @return the CORS configuration for the request
     */
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        return config;
    }
}
