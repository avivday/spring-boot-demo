package com.aviv.springbootdemo.service.user.implementation;

import com.aviv.springbootdemo.dao.postgres.mock.user.UserDaoMock;
import com.aviv.springbootdemo.model.user.Gender;
import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.controllers.v1.user.models.CreateUserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserServiceTest {

    private IUserService _userService;

    @BeforeEach
    void setUp() {
        this._userService = new UserService(new UserDaoMock());
    }

    @Test
    @DirtiesContext
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
    @DirtiesContext
    void getUserByUUID() {
        User user = this._userService.getAllUsers().get(0);
        User findUser = this._userService.getUserByUUID(user.getUserUid());

        assertThat(findUser).isEqualTo(user);
    }

    @Test
    @DirtiesContext
    void updateUser() {
        User user = this._userService.getAllUsers().get(0);
        User updatedUser = new User(
                user.getUserUid(), user.getFirstName(), user.getLastName(), user.getGender(), 30, "updated@email.com");
        this._userService.updateUser(updatedUser);

        assertThat(this._userService.getUserByUUID(user.getUserUid())).isEqualTo(updatedUser);
    }

    @Test
    @DirtiesContext
    void removeUserByUUID() {
        User user = this._userService.getAllUsers().get(0);
        this._userService.removeUserByUUID(user.getUserUid());

        assertThat(this._userService.getUserByUUID(user.getUserUid())).isNull();
    }

    @Test
    @DirtiesContext
    void insertUser() {
        CreateUserModel user = new CreateUserModel("Jane", "Doe", Gender.FEMALE, 18, "jane@doe.com");
        User newUser = this._userService.insertUser(user);

        assertThat(newUser).isNotNull();
        assertThat(newUser.getFirstName()).isEqualTo("Jane");
        assertThat(newUser.getLastName()).isEqualTo("Doe");
        assertThat(newUser.getGender()).isEqualTo(Gender.FEMALE);
        assertThat(newUser.getAge()).isEqualTo(18);
        assertThat(newUser.getEmail()).isEqualTo("jane@doe.com");
    }
}