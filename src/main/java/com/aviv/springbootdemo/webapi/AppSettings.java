package com.aviv.springbootdemo.webapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSettings {

    @Value("${app.settings.exceptions.detail}")
    private boolean detailedExceptions;

    @Bean
    public boolean showDetailedExceptions() {
        return this.detailedExceptions;
    }
}
