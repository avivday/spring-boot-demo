package com.aviv.springbootdemo.dao.postgres.mock.user;

import com.aviv.springbootdemo.dao.postgres.contract.user.IUserDao;
import com.aviv.springbootdemo.model.user.Gender;
import com.aviv.springbootdemo.model.user.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("UserDaoMock")
@ConditionalOnProperty(prefix = "postgres", name = "mock", havingValue = "true")
public class UserDaoMock implements IUserDao {

    private static Map<UUID, User> fakeData;

    static {
        fakeData = new HashMap<>();
        UUID userUUID = UUID.randomUUID();
        fakeData.put(userUUID, new User(userUUID, "Joe", "Doe", Gender.MALE, 22, "joe@doe.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(fakeData.values());
    }

    @Override
    public User getUserByUUID(UUID userUid) {
        return fakeData.get(userUid);
    }

    @Override
    public void updateUser(User user) {
        fakeData.put(user.getUserUid(), user);
    }

    @Override
    public void removeUserByUUID(UUID userUid) {
        fakeData.remove(userUid);
    }

    @Override
    public void insertUser(UUID userUid, User user) {
        fakeData.put(userUid, user);
    }
}
