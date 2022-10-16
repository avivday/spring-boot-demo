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

    private static Map<UUID, User> fakeData;

    static {
        fakeData = new HashMap<>();
        UUID superAdminUUID = UUID.fromString("15001508-e4d2-4fd9-9fa8-91dd89c17373");
        fakeData.put(superAdminUUID, new User(superAdminUUID, "Joe", "Doe", Gender.MALE, 22, "joe@doe.com", AuthRoles.SUPER_ADMIN));
        UUID adminUUID = UUID.fromString("c54cb992-011f-49a4-a240-c41949862488");
        fakeData.put(adminUUID, new User(adminUUID, "Joe", "Doe", Gender.MALE, 22, "joe@doe.com", AuthRoles.ADMIN));
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
