package com.aviv.springbootdemo.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class User {

    /**
     * User identifier
     */
    private final UUID userUid;

    /**
     * First name
     */
    @NotNull
    private final String firstName;

    /**
     * Last name
     */
    @NotNull
    private final String lastName;

    /**
     * Gender
     */
    @NotNull
    private final Gender gender;

    /**
     * Age
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 120)
    private final Integer age;

    /**
     * Email
     */
    @NotNull
    @Email
    private final String email;

    /**
     * User role
     */
    private final String role;

    public User(
            @JsonProperty("userUid") UUID userUid,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("age") Integer age,
            @JsonProperty("email") String email,
            @JsonProperty("role") String role) {
        this.userUid = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public UUID getUserUid() {
        return userUid;
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
        return role;
    }
}
