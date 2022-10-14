package com.aviv.springbootdemo.dao.postgres.implementation.user;

import com.aviv.springbootdemo.dao.postgres.contract.user.IUserDao;
import com.aviv.springbootdemo.model.user.Gender;
import com.aviv.springbootdemo.model.user.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("UserDao")
@Primary
@ConditionalOnProperty(prefix = "postgres", name = "mock", havingValue = "false")
public class UserDao implements IUserDao {

    // real implementation, connect to your db and return real data.
    // For the example, we say we use postgres and we will return "real" data.
    private static Map<UUID, User> _realData;

    public UserDao() {
        // inject and use your own database, for now we will add "real" data here manually.
        this._realData = new HashMap<>();
        UUID userUUID = UUID.randomUUID();
        this._realData.put(userUUID, new User(userUUID, "Aviv", "Day", Gender.MALE, 22, "avivday@gmail.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(this._realData.values());
    }

    @Override
    public User getUserByUUID(UUID userUid) {
        return this._realData.get(userUid);
    }

    @Override
    public void updateUser(User user) {
        this._realData.put(user.getUserUid(), user);
    }

    @Override
    public void removeUserByUUID(UUID userUid) {
        this._realData.remove(userUid);
    }

    @Override
    public void insertUser(UUID userUid, User user) {
        this._realData.put(userUid, user);
    }
}
