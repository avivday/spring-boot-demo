package com.aviv.springbootdemo.security.contract;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.webapi.security.AuthRoles;

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
     * Validate
     * @param user
     */
    void validateUserRole(User user, String[] allowedRoles);

    /**
     * Get user identifier from the token
     * @param token
     * @return user identifier (UUID)
     */
    UUID getUserUidFromToken(String token);
}