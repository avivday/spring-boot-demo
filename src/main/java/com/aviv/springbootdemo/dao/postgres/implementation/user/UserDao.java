package com.aviv.springbootdemo.dao.postgres.implementation.user;

import com.aviv.springbootdemo.dao.postgres.contract.user.IUserDao;
import com.aviv.springbootdemo.model.user.Gender;
import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.webapi.security.AuthRoles;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("UserDao")
@Primary
@ConditionalOnProperty(prefix = "app.settings.dao.postgres", name = "mock", havingValue = "false")
public class UserDao implements IUserDao {

    // real implementation, connect to your db and return real data.
    // For the example, we say we use postgres and we will return "real" data.
    private static Map<String, User> _realData;

    public UserDao() {
        // inject and use your own database, for now we will add "real" data here manually.
        this._realData = new HashMap<>();
        String username = "avivday";
        this._realData.put(username, new User(UUID.randomUUID(), username, "Aviv", "Day", Gender.MALE, 22, "avivday@gmail.com", AuthRoles.SUPER_ADMIN));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(this._realData.values());
    }

    @Override
    public User getUserByUsername(String username) {
        return this._realData.get(username);
    }

    @Override
    public void updateUser(User user) {
        this._realData.put(user.getUsername(), user);
    }

    @Override
    public void removeUser(String username) {
        this._realData.remove(username);
    }

    @Override
    public void insertUser(User user) {
        this._realData.put(user.getUsername(), user);
    }
}
