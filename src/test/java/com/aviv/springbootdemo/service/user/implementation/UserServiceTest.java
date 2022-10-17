package com.aviv.springbootdemo.service.user.implementation;

import com.aviv.springbootdemo.dao.postgres.mock.user.UserDaoMock;
import com.aviv.springbootdemo.model.user.Gender;
import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.controllers.v1.user.models.CreateUserModel;
import com.aviv.springbootdemo.webapi.security.AuthRoles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;

class UserServiceTest {

    private IUserService _userService;

    @BeforeEach
    void setUp() {
        this._userService = new UserService(new UserDaoMock(), null); // TODO: change to null
    }

    @Test
    void getAllUsers() {
        List<User> users = this._userService.getAllUsers();
        assertThat(users.size()).isEqualTo(1);
        User user = users.get(0);
        assertThat(user.getFirstName()).isEqualTo("Joe");
        assertThat(user.getLastName()).isEqualTo("Doe");
        assertThat(user.getAge()).isEqualTo(22);
        assertThat(user.getGender()).isEqualTo(Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("joe@doe.com");
        assertThat(user.getUserUid()).isNotNull();
    }

    @Test
    void updateUser() {
        User user = this._userService.getAllUsers().get(0);
        User updatedUser = new User(
                user.getUserUid(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getGender(), 30, "updated@email.com", AuthRoles.SUPER_ADMIN);
        this._userService.updateUser(updatedUser);

        assertThat(this._userService.getUserByUsername(user.getUsername())).isEqualTo(updatedUser);
    }

    @Test
    void removeUser() {
        User user = this._userService.getAllUsers().get(0);
        this._userService.removeUser(user.getUsername());

        assertThrows(NotFoundException.class, () -> this._userService.getUserByUsername(user.getUsername()));
    }

    @Test
    void insertUser() {
        CreateUserModel user = new CreateUserModel("johnmock","John", "Mock", Gender.MALE, 18, "jane@doe.com", AuthRoles.SUPER_ADMIN);
        User newUser = this._userService.insertUser(user);

        assertThat(newUser).isNotNull();
        assertThat(newUser.getUsername()).isEqualTo("johnmock");
        assertThat(newUser.getFirstName()).isEqualTo("John");
        assertThat(newUser.getLastName()).isEqualTo("Mock");
        assertThat(newUser.getGender()).isEqualTo(Gender.MALE);
        assertThat(newUser.getAge()).isEqualTo(18);
        assertThat(newUser.getEmail()).isEqualTo("jane@doe.com");
        assertThat(newUser.getRole()).isEqualTo(AuthRoles.SUPER_ADMIN);
    }
}