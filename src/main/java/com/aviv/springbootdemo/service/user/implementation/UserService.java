package com.aviv.springbootdemo.service.user.implementation;

import com.aviv.springbootdemo.dao.postgres.contract.user.IUserDao;
import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.controllers.v1.user.models.CreateUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserDao _userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this._userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return this._userDao.getAllUsers();
    }

    @Override
    public User getUserByUUID(UUID userUid) {
        return this._userDao.getUserByUUID(userUid);
    }

    @Override
    public void updateUser(User user) {
        this._userDao.updateUser(user);
    }

    @Override
    public void removeUserByUUID(UUID userUid) {
        this._userDao.removeUserByUUID(userUid);
    }

    @Override
    public User insertUser(CreateUserModel user) {
        UUID newUserUid = UUID.randomUUID();
        User newUser = new User(newUserUid, user.getFirstName(), user.getLastName(), user.getGender(), user.getAge(), user.getEmail());
        this._userDao.insertUser(newUserUid, newUser);
        return newUser;
    }
}
