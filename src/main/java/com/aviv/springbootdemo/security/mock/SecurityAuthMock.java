package com.aviv.springbootdemo.security.mock;

import com.aviv.springbootdemo.security.contract.ISecurityAuth;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@ConditionalOnProperty(prefix = "app.settings.security.jwt", name = "mock", havingValue = "true")
public class SecurityAuthMock implements ISecurityAuth {
    // TODO: check and make mock better
    @Override
    public void login(String user, String password, HttpServletResponse response) {
        // Not forbidden -> mock
    }

    @Override
    public void validateToken(String token) {

    }

    @Override
    public UUID getUserUidFromToken(String token) {
        return null;
    }
}
