package com.aviv.springbootdemo.webapi.controllers.v1.user.models;

import com.aviv.springbootdemo.model.user.Gender;

import java.util.Optional;

public class CreateUserModel {
    /**
     * First name
     */
    private String firstName;

    /**
     * Last Name
     */
    private String lastName;

    /**
     * Gender
     */
    private Gender gender;

    /**
     * Age
     */
    private Integer age;

    /**
     * Email
     */
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
