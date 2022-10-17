package com.aviv.springbootdemo.service.user.implementation;

import com.aviv.springbootdemo.dao.postgres.contract.user.IUserDao;
import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.AppContext;
import com.aviv.springbootdemo.webapi.controllers.v1.user.models.CreateUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserDao _userDao;
    private AppContext appContext;

    @Autowired
    public UserService(IUserDao userDao, AppContext appContext) {
        this._userDao = userDao;
        this.appContext = appContext;
    }

    @Override
    public List<User> getAllUsers() {
        return this._userDao.getAllUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        User user;
        try {
            user = this._userDao.getUserByUsername(username);
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }

        return user;
    }

    @Override
    public void updateUser(User user) {
        this._userDao.updateUser(user);
    }

    @Override
    public void removeUser(String username) {
        this._userDao.removeUser(username);
    }

    @Override
    public User insertUser(CreateUserModel user) {
        User newUser = new User(UUID.randomUUID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getGender(), user.getAge(), user.getEmail(), user.getRole());
        this._userDao.insertUser(newUser);

        return newUser;
    }
}
