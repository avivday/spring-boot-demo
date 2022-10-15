package com.aviv.springbootdemo.webapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSettings {

    @Value("${app.settings.exceptions.detail}")
    private boolean detailedExceptions;

    @Value("${app.settings.jwt.secret}")
    private String jwtSecret;

    @Value("${app.settings.jwt.expiration-milliseconds}")
    private int jwtExpirationInMs;

    public boolean showDetailedExceptions() {
        return this.detailedExceptions;
    }

    public String getJwtSecret() {
        return this.jwtSecret;
    }

    public int getJwtExpirationInMs() {
        return this.jwtExpirationInMs;
    }
}
