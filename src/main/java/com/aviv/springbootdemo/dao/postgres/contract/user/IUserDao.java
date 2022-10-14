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
     *
     * @param userUid
     * @return
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
     */
    void insertUser(UUID userUid, User user);
}
