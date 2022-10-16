package com.aviv.springbootdemo.webapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSettings {

    @Value("${app.settings.exceptions.detail}")
    private boolean detailedExceptions;

    @Value("${app.settings.security.jwt.secret}")
    private String jwtSecret;

    @Value("${app.settings.security.jwt.expiration-milliseconds}")
    private int jwtExpirationInMs;

    @Value("${app.settings.security.jwt.cookie-name}")
    private String jwtCookieName;

    public boolean showDetailedExceptions() {
        return this.detailedExceptions;
    }

    public String getJwtSecret() {
        return this.jwtSecret;
    }

    public int getJwtExpirationInMs() {
        return this.jwtExpirationInMs;
    }

    public String getJwtCookieName() {
        return this.jwtCookieName;
    }
}
