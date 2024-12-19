package com.example.AcademiFlow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Refresh Token requests.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDto {

    /**
     * The refresh token used to generate a new access token.
     */
    private String token;
}
