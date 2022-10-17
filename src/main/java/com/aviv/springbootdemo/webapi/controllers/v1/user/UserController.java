package com.aviv.springbootdemo.webapi.controllers.v1.user;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.controllers.v1.user.models.CreateUserModel;
import com.aviv.springbootdemo.webapi.security.AuthRoles;
import com.aviv.springbootdemo.webapi.security.Authorize;
import com.github.therapi.runtimejavadoc.repack.com.eclipsesource.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * User Controller
 */
@Validated
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
     * Get user by username
     * @param username username
     * @return User
     */
    @RequestMapping(path = "{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable("username") String username) throws Exception {
        return this._userService.getUserByUsername(username);
    }

    /**
     * Insert a new user
     * @param user user to insert
     */
    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void insertUser(@Valid CreateUserModel user) {
        this._userService.insertUser(user);
    }

    /**
     * Update user
     * @param username
     * @param user User to update
     */
    @RequestMapping(path = "{username}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("username") String username, User user) {
        this._userService.updateUser(user);
    }

    /**
     * Remove user
     * @param username username
     */
    @RequestMapping(path = "{username}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable("username") String username) {
        this._userService.removeUser(username);
    }

    /**
     * This is a protected route as an example only
     * @return First user from the list of users (Example)
     */
    @Authorize(AuthRoles.SUPER_ADMIN)
    @RequestMapping(path = "/special", method = RequestMethod.GET)
    public User adminOnlyRoute() {
        // some special magic here
        return this._userService.getAllUsers().get(0);
    }
}
