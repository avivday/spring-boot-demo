package com.aviv.springbootdemo.service.user.contract;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.webapi.controllers.user.models.CreateUserModel;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    /**
     * Get all users
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Get user by user UUID
     * @param userUid - requested user UUID
     * @return User
     */
    User getUserByUUID(UUID userUid);

    /**
     * Update user
     * @param user updated user details
     */
    void updateUser(User user);

    /**
     * Remove user by UUID
     * @param userUid - requested user UUID
     */
    void removeUserByUUID(UUID userUid);

    /**
     * Create a new user
     * @param user new user to add
     * @return the added new user
     */
    User insertUser(CreateUserModel user);
}
