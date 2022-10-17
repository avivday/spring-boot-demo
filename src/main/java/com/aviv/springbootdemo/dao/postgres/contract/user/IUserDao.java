package com.aviv.springbootdemo.dao.postgres.contract.user;

import com.aviv.springbootdemo.model.user.User;

import java.util.List;
import java.util.UUID;

public interface IUserDao {
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
     * Remove user by UUID
     * @param username - requested user UUID
     */
    void removeUser(String username);

    /**
     * Create a new user
     * @param user new user to add
     */
    void insertUser(User user);
}
