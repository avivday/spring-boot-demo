package com.aviv.springbootdemo.webapi.controllers.user;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.controllers.user.models.CreateUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "api/v1/users"
)
public class UserController {

    private IUserService _userService;

    @Autowired
    public UserController(IUserService userService) {
        this._userService = userService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return this._userService.getAllUsers();
    }

    @RequestMapping(path = "{userUid}", method = RequestMethod.GET)
    public User getUserByUid(@PathVariable("userUid") UUID userUid) {
        return this._userService.getUserByUUID(userUid);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void insertUser(CreateUserModel user) {
        this._userService.insertUser(user);
    }

    @RequestMapping(path = "{userUid}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("userUid") UUID userUid, User user) {
        this._userService.updateUser(user);
    }

    @RequestMapping(path = "{userUid}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("userUid") UUID userUid) {
        this._userService.removeUserByUUID(userUid);
    }
}
