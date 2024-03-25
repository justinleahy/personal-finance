package com.justinleahy.personalfinance.user;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CRUD Test Status

Create : DONE
Read: DONE
Update: DONE
Delete: DONE

Create is done in every test
 */

@SpringBootTest
class UserRepositoryTests {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryTests.class);

    @Autowired
    private UserRepository repository;

    @Test
    void testFindUserById() {
        User user = new User("John", "Doe", "johndoe1", "testPassword");
        repository.save(user);

        User foundUser = repository.findById(user.getId()).orElse(null);

        assertNotNull(foundUser);
        assertEquals(user.getUserName(), foundUser.getUserName());
        assertEquals(user.getFirstName(), foundUser.getFirstName());
        assertEquals(user.getLastName(), foundUser.getLastName());

        log.info("User was found: {}", foundUser);
    }

    @Test
    void testFindByUserName() {
        User user = new User("Justin", "Leahy", "justinleahy", "testPassword");
        repository.save(user);

        List<User> foundUsers = repository.findByUserName(user.getUserName());

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals(user.getUserName(), foundUsers.getFirst().getUserName());
        assertEquals(user.getFirstName(), foundUsers.getFirst().getFirstName());
        assertEquals(user.getLastName(), foundUsers.getFirst().getLastName());

        log.info("Users were found: {}", foundUsers);
    }

    @Test
    void testDeleteUser() {
        User user = new User("John", "Doe", "johndoe3", "testPassword");
        repository.save(user);

        repository.deleteById(user.getId());

        User deletedUser = repository.findById(user.getId()).orElse(null);

        assertNull(deletedUser);

        log.info("User was deleted: {}", user);
    }

    @Test
    void testUpdateUser() {
        User user = new User("John", "Doe", "johndoe4", "testPassword");
        repository.save(user);

        user.setUserName("newusername");
        repository.save(user);

        User updatedUser = repository.findById(user.getId()).orElse(null);

        assertNotNull(updatedUser);
        assertEquals("newusername", updatedUser.getUserName());

        log.info("User was updated: {}", updatedUser);
    }
}
