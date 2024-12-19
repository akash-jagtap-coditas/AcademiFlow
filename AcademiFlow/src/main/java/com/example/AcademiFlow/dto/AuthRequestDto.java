package com.example.AcademiFlow.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for authentication requests.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDto {

    /**
     * The username of the user.
     */
    @Size(min = 8, max = 20, message = "Username should be between 8 to 20 characters.")
    @NotEmpty(message = "Username should not be empty")
    @Pattern(regexp = ".*[@].*[.].*", message = "Username must contain '@' and '.'")
    private String username;

    /**
     * The password of the user.
     */
    @Size(min = 8, max = 20, message = "Password should be between 8 to 20 characters.")
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
