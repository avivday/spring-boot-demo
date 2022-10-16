package com.aviv.springbootdemo.security.jwt.contract;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface IJwtSecurityService {

    /**
     * Generate token
     * @param authentication
     * @return JWT Token
     */
    String generateToken(Authentication authentication);

    /**
     * Get user uid (subject claim) from token
     * @param token JWT token
     * @return user uid from payload
     */
    UUID getUserUidFromToken(String token);

    /**
     * Validate Jwt Token
     * @param token JWT Token
     * @return Is the token valid or not
     */
    boolean validateToken(String token);
}
