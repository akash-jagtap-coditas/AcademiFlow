package com.example.AcademiFlow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Refresh Token responses.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {

    /**
     * The new access token generated using the refresh token.
     */
    String accessToken;
}
