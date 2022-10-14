package com.aviv.springbootdemo.webapi.controllers.user.models;

import com.aviv.springbootdemo.model.user.Gender;

import java.util.Optional;

public class CreateUserModel {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Integer age;
    private String email;

    public CreateUserModel(String firstName, String lastName, Gender gender, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
