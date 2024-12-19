package com.example.AcademiFlow.controller;

import com.example.AcademiFlow.dto.*;
import com.example.AcademiFlow.repository.UsersRepository;
import com.example.AcademiFlow.service.Impl.JwtServiceImpl;
import com.example.AcademiFlow.service.Impl.UserServiceImpl;
import com.example.AcademiFlow.service.TokenService;
import com.example.AcademiFlow.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle authentication requests.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "APIs for user authentication and token management")
public class AuthController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * Authenticates the user and generates a JWT token.
     *
     * @param authRequestDTO the authentication request containing username and password
     * @return a response containing the JWT access token and refresh token
     * @throws UsernameNotFoundException if the authentication request is invalid
     */
    @Operation(summary = "Authenticate user", description = "Authenticates the user and generates a JWT token.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
JwtResponseDto jwtToken = null;
        if (authentication.isAuthenticated()) {

             jwtToken = JwtResponseDto.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                     .roles(userService.getRoles(authRequestDTO.getUsername()))
                    .build();

            return ResponseUtil.success(jwtToken,"User logged In successfully");
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }


}
