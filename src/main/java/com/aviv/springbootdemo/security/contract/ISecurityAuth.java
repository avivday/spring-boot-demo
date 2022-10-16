package com.aviv.springbootdemo.security.contract;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public interface ISecurityAuth {

    /**
     * Login
     *
     * @param user
     * @param password
     * @param response
     */
    void login(String user, String password, HttpServletResponse response);

    /**
     * Validates JWT Token
     * @param token jwt token
     */
    void validateToken(String token);

    /**
     * Get user identifier from the token
     * @param token
     * @return user identifier (UUID)
     */
    UUID getUserUidFromToken(String token);
}