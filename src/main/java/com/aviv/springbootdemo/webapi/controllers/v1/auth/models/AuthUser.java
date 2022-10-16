package com.aviv.springbootdemo.webapi.controllers.v1.auth.models;

public class AuthUser {
    private String user;
    private String Password;

    public AuthUser(String user, String password) {
        this.user = user;
        Password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return Password;
    }
}
