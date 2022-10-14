package com.aviv.springbootdemo.webapi.controllers.v1.user;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.controllers.v1.user.models.CreateUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * User Controller
 */
@RestController()
@RequestMapping(
        path = "/api/v1/users"
)
public class UserController {

    private IUserService _userService;

    @Autowired
    public UserController(IUserService userService) {
        this._userService = userService;
    }

    /**
     * Get all users
     * @return List of users
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return this._userService.getAllUsers();
    }

    /**
     * Get user by uid
     * @param userUid user identifier
     * @return User
     */
    @RequestMapping(path = "{userUid}", method = RequestMethod.GET)
    public User getUserByUid(@PathVariable("userUid") UUID userUid) {
        return this._userService.getUserByUUID(userUid);
    }

    /**
     * Insert a new user
     * @param user user to insert
     */
    @RequestMapping(path = "", method = RequestMethod.POST)
    public void insertUser(CreateUserModel user) {
        this._userService.insertUser(user);
    }

    /**
     * Update user
     * @param userUid user identifier to update
     * @param user User to update
     */
    @RequestMapping(path = "{userUid}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("userUid") UUID userUid, User user) {
        this._userService.updateUser(user);
    }

    /**
     * Remove user
     * @param userUid user identifier to delete
     */
    @RequestMapping(path = "{userUid}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("userUid") UUID userUid) {
        this._userService.removeUserByUUID(userUid);
    }
}
