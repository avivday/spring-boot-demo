package com.aviv.springbootdemo.dao.postgres.mock.user;

import com.aviv.springbootdemo.dao.postgres.contract.user.IUserDao;
import com.aviv.springbootdemo.model.user.Gender;
import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.webapi.security.AuthRoles;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("UserDaoMock")
@ConditionalOnProperty(prefix = "app.settings.dao.postgres", name = "mock", havingValue = "true")
public class UserDaoMock implements IUserDao {

    private static Map<String, User> fakeData;

    static {
        fakeData = new HashMap<>();
        fakeData.put("joedoe", new User(UUID.randomUUID(), "joedoe", "Joe", "Doe", Gender.MALE, 22, "joe@doe.com", AuthRoles.SUPER_ADMIN));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(fakeData.values());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.fakeData.get(username);
    }

    @Override
    public void updateUser(User user) {
        fakeData.put(user.getUsername(), user);
    }

    @Override
    public void removeUser(String username) {
        fakeData.remove(username);
    }

    @Override
    public void insertUser(User user) {
        fakeData.put(user.getUsername(), user);
    }
}
