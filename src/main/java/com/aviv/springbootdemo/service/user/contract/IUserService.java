package com.aviv.springbootdemo.service.user.contract;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.webapi.controllers.user.models.CreateUserModel;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> getAllUsers();
    User getUserByUUID(UUID userUid);
    void updateUser(User user);
    void removeUserByUUID(UUID userUid);
    User insertUser(CreateUserModel user);
}
