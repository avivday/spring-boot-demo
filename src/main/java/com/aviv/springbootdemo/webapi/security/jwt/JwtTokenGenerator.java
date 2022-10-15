package com.aviv.springbootdemo.webapi.security.jwt;

import com.aviv.springbootdemo.webapi.AppSettings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenGenerator {

    private AppSettings appSettings;

    @Autowired
    public JwtTokenGenerator(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + this.appSettings.getJwtExpirationInMs());

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512,this.appSettings.getJwtSecret())
                .compact();

        return token;
    }
}
