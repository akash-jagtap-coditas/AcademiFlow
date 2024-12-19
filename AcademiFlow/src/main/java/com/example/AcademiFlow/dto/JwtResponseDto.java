package com.example.AcademiFlow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) for JWT responses.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDto {

    /**
     * The JWT access token.
     */
    private String accessToken;

    private List<String> roles;
}
