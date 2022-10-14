package com.aviv.springbootdemo.dao.postgres.contract.user;

import com.aviv.springbootdemo.model.user.User;

import java.util.List;
import java.util.UUID;

public interface IUserDao {
    List<User> getAllUsers();
    User getUserByUUID(UUID userUid);
    void updateUser(User user);
    void removeUserByUUID(UUID userUid);
    void insertUser(UUID userUid, User user);
}
