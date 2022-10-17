package com.aviv.springbootdemo.security.contract;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.webapi.security.AuthRoles;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public interface ISecurityAuth {

    /**
     * Login
     *
     * @param username
     * @param password
     * @param response
     * @return Logged in user
     */
    User login(String username, String password, HttpServletResponse response);

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
     * Get username from the token
     * @param token
     * @return username
     */
    String getUsernameFromToken(String token);
}