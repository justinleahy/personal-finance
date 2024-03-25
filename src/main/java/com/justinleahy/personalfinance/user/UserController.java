package com.justinleahy.personalfinance.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
CRUD Status

Create : DONE
Read: DONE
Update: DONE
Delete: DONE

Create is done in every test
 */
@RestController
public class UserController {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> postUser(@RequestBody User user) {
        // Two users cannot have the same username
        if (!userRepository.findByUserName(user.getUserName()).isEmpty()) {
            log.error("New user creation failed: User with username {} already exists", user.getUserName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPasswordHash());

        userRepository.save(newUser);
        log.info("New user created: {}", newUser);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        log.info("Fetching user: {}", optionalUser.get());
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = optionalUser.get();
        log.info("Updating user: {}", user);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setUserName(updatedUser.getUserName());
        user.setPasswordHash(updatedUser.getPasswordHash());

        userRepository.save(user);
        log.info("Updated user: {}", user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        log.info("Deleted user at id {}", id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
