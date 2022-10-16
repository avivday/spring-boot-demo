package com.aviv.springbootdemo.security.jwt.contract;

import java.util.UUID;

public interface IJwtSecurityService {

    /**
     * Generate token
     * @param subject - the token subject (username, id, etc...)
     * @return JWT Token
     */
    String generateToken(String subject);

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
