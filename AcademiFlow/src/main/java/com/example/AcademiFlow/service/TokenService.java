package com.example.AcademiFlow.service;

/**
 * Service interface for managing token blacklisting.
 */
public interface TokenService {

    /**
     * Adds a token to the blacklist.
     *
     * @param token the token to blacklist
     */
    void blacklistToken(String token);

    /**
     * Checks if a token is blacklisted.
     *
     * @param token the token to check
     * @return true if the token is blacklisted, false otherwise
     */
    boolean isTokenBlacklisted(String token);
}
