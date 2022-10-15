package com.aviv.springbootdemo.webapi.controllers.v1.user.models;

import com.aviv.springbootdemo.model.user.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CreateUserModel {
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
