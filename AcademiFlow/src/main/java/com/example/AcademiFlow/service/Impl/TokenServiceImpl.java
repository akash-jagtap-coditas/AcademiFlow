package com.example.AcademiFlow.service.Impl;

import com.example.AcademiFlow.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the TokenService interface for managing token blacklisting.
 */
@Service
public class TokenServiceImpl implements TokenService {

    private Set<String> blacklist = new HashSet<>();

    /**
     * Adds a token to the blacklist.
     *
     * @param token the token to blacklist
     */
    public void blacklistToken(String token) {

        blacklist.add(token);
    }

    /**
     * Checks if a token is blacklisted.
     *
     * @param token the token to check
     * @return true if the token is blacklisted, false otherwise
     */
    public boolean isTokenBlacklisted(String token) {

        return blacklist.contains(token);
    }
}
