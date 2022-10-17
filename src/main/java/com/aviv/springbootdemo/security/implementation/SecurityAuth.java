package com.aviv.springbootdemo.security.implementation;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.security.contract.ISecurityAuth;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;
import java.util.Arrays;

@Service
@Primary
@ConditionalOnProperty(prefix = "app.settings.security.auth", name = "mock", havingValue = "false")
public class SecurityAuth implements ISecurityAuth {

    private JwtSecurityService _jwtSecurity;
    private IUserService _userService;
    private AppSettings _appSettings;

    @Autowired
    public SecurityAuth(JwtSecurityService jwtSecurity, IUserService userService, AppSettings appSettings) {
        this._jwtSecurity = jwtSecurity;
        this._userService = userService;
        this._appSettings = appSettings;
    }

    @Override
    public User login(String username, String password, HttpServletResponse response) {
        User user = this._userService.getUserByUsername(username);
        Cookie jwtCookie = new Cookie(this._appSettings.getJwtCookieName(), this._jwtSecurity.generateToken(username));
        jwtCookie.setHttpOnly(true);
        response.addCookie(jwtCookie);

        return user;
    }

    @Override
    public void validateToken(String token) {
        this._jwtSecurity.validateToken(token);
    }

    @Override
    public void validateUserRole(User user, String[] allowedRoles) {
        if(!Arrays.asList(allowedRoles).contains(user.getRole())) {
            throw new ForbiddenException("Forbidden");
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        return this._jwtSecurity.getUsernameFromToken(token);
    }
}
