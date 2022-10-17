package com.aviv.springbootdemo.security.mock;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.security.contract.ISecurityAuth;
import com.aviv.springbootdemo.service.user.implementation.UserService;
import com.aviv.springbootdemo.webapi.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;
import java.util.Arrays;

@Service
@ConditionalOnProperty(prefix = "app.settings.security.auth", name = "mock", havingValue = "true")
public class SecurityAuthMock implements ISecurityAuth {

    private AppSettings _appSettings;
    private UserService _userService;

    @Autowired
    public SecurityAuthMock(AppSettings _appSettings, UserService userService) {
        this._appSettings = _appSettings;
        this._userService = userService;
    }

    @Override
    public User login(String username, String password, HttpServletResponse response) {
        // user is super_admin, ticket is valid until 2498
        String mockedJwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2Vkb2UiLCJpYXQiOjE2NjU5MjYzMjIsImV4cCI6MTY2NjUzMTEyMjJ9.TeUJZuW3pSCkh4epQnFELpPAvN4f1pG5WQUPOeNXlyY";
        Cookie cookie = new Cookie(this._appSettings.getJwtCookieName(), mockedJwt);
        response.addCookie(cookie);

        return this._userService.getUserByUsername(username);
    }

    @Override
    public void validateToken(String token) {
        // token is valid on mock, no need to do anything
    }

    @Override
    public void validateUserRole(User user, String[] allowedRoles) {
        if(!Arrays.asList(allowedRoles).contains(user.getRole())) {
            throw new ForbiddenException("Forbidden");
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        return "joedoe";
    }
}
