package com.aviv.springbootdemo.webapi.controllers.v1.user.models;

import com.aviv.springbootdemo.model.user.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateUserModel {

    /**
     * Username
     */
    @NotNull
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
    @NotNull
    private String role;

    public CreateUserModel(
            @JsonProperty("username") String username,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("age") Integer age,
            @JsonProperty("email") String email,
            @JsonProperty("role") String role) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
