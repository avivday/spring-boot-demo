package com.aviv.springbootdemo.security.mock;

import com.aviv.springbootdemo.security.contract.ISecurityAuth;
import com.aviv.springbootdemo.service.user.implementation.UserService;
import com.aviv.springbootdemo.webapi.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@ConditionalOnProperty(prefix = "app.settings.security.auth", name = "mock", havingValue = "true")
public class SecurityAuthMock implements ISecurityAuth {

    private AppSettings _appSettings;

    @Autowired
    public SecurityAuthMock(AppSettings _appSettings) {
        this._appSettings = _appSettings;
    }

    @Override
    public void login(String user, String password, HttpServletResponse response) {
        Cookie cookie = new Cookie(this._appSettings.getJwtCookieName(), "fake.jwt.cookie");
        response.addCookie(cookie);
    }

    @Override
    public void validateToken(String token) {
        // token is valid on mock, no need to do anything
    }

    @Override
    public UUID getUserUidFromToken(String token) {
        return UUID.fromString("15001508-e4d2-4fd9-9fa8-91dd89c17373");
    }
}
