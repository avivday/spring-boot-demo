package com.aviv.springbootdemo.webapi.security;

import com.aviv.springbootdemo.webapi.security.filters.FilterRoles;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // enable in memory based authentication with a user named "user" and "admin"
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("password")
                .roles(FilterRoles.ADMIN)
                .and()
                .withUser("superAdmin")
                .password("password")
                .roles(FilterRoles.ADMIN, FilterRoles.SUPER_ADMIN);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/public/**").permitAll().anyRequest()
                .hasRole(FilterRoles.ADMIN)
                .and()
                .formLogin()
                .permitAll();
    }
    // Possibly more overridden methods ...
}