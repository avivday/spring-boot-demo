package com.aviv.springbootdemo.service.user.contract;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.webapi.controllers.v1.user.models.CreateUserModel;
import javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    /**
     * Get all users
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Get user by username
     * @param username - username
     * @return Requested user
     */
    User getUserByUsername(String username);

    /**
     * Update user
     * @param user updated user details
     */
    void updateUser(User user);

    /**
     * Remove user
     * @param userUid - requested user UUID
     */
    void removeUser(String username);

    /**
     * Create a new user
     * @param user new user to add
     * @return the added new user
     */
    User insertUser(CreateUserModel user);
}
