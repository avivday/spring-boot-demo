package com.aviv.springbootdemo.security.jwt.mock;

import com.aviv.springbootdemo.security.jwt.contract.IJwtSecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtSecurityServiceMock implements IJwtSecurityService {
    @Override
    public String generateToken(Authentication authentication) {
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
