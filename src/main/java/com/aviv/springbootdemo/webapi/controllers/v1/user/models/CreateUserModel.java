package com.aviv.springbootdemo.webapi.controllers.v1.user.models;

import com.aviv.springbootdemo.model.user.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateUserModel {

    /**
     * Username
     */
    public String username;

    /**
     * First name
     */
    @NotNull
    private String firstName;

    /**
     * Last Name
     */
    @NotNull
    private String lastName;

    /**
     * Gender
     */
    @NotNull
    private Gender gender;

    /**
     * Age
     */
    @NotNull
    @Min(value = 0)
    private Integer age;

    /**
     * Email
     */
    @NotNull
    @Email
    private String email;

    /**
     * User role
     */
    private String role;

    public CreateUserModel(String username, String firstName, String lastName, Gender gender, Integer age, String email, String role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
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

    public String getRole() {
        return this.role;
    }
}
