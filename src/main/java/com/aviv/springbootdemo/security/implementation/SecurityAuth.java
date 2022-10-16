package com.aviv.springbootdemo.security.implementation;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.security.contract.ISecurityAuth;
import com.aviv.springbootdemo.webapi.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;
import java.util.Arrays;
import java.util.UUID;

@Service
@Primary
@ConditionalOnProperty(prefix = "app.settings.security.auth", name = "mock", havingValue = "false")
public class SecurityAuth implements ISecurityAuth {

    private JwtSecurityService _jwtSecurity;
    private AppSettings _appSettings;

    @Autowired
    public SecurityAuth(JwtSecurityService jwtSecurity, AppSettings appSettings) {
        this._jwtSecurity = jwtSecurity;
        this._appSettings = appSettings;
    }

    @Override
    public void login(String user, String password, HttpServletResponse response) {
        if(!user.equalsIgnoreCase("user") || !password.equalsIgnoreCase("1234")) {
            throw new ForbiddenException("Wrong username or password");
        }

        String userUid = "3091c093-803b-437f-9571-10791f35b4f1";
        Cookie jwtCookie = new Cookie(this._appSettings.getJwtCookieName(), this._jwtSecurity.generateToken(userUid));
        jwtCookie.setHttpOnly(true);
        response.addCookie(jwtCookie);
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
    public UUID getUserUidFromToken(String token) {
        return this._jwtSecurity.getUserUidFromToken(token);
    }
}
