package com.aviv.springbootdemo.security.jwt.mock;

import com.aviv.springbootdemo.security.jwt.contract.IJwtSecurityService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@ConditionalOnProperty(prefix = "app.settings.security.jwt", name = "mock", havingValue = "true")
public class JwtSecurityServiceMock implements IJwtSecurityService {
    @Override
    public String generateToken(String subject) {
        return "mock.jwt.fake";
    }

    @Override
    public UUID getUserUidFromToken(String token) {
        return UUID.randomUUID(); // TODO: create better mock to make sure you get user even if dao is on real values
    }

    @Override
    public boolean validateToken(String token) {
        return true;
    }
}
