package org.lovepacs.controllers;

import org.lovepacs.json.UserJson;
import org.lovepacs.models.User;
import org.lovepacs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User getUser(@PathVariable("id") final int id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void disableUser(@PathVariable("id") final int id) {
        User user = userRepository.findOne(id);
        if (user != null) {
            user.setEnabled(false);
            userRepository.save(user);
        }
    }

    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PUT)
    void enableUser(@PathVariable("id") final int id) {
        User user = userRepository.findOne(id);
        if (user != null) {
            user.setEnabled(true);
            userRepository.save(user);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    void createUser(@RequestBody UserJson userJson) {

        User user = new User();
        user.setName(userJson.getName());
        user.setPassword(passwordEncoder.encode(userJson.getPassword()));
        user.setEnabled(userJson.getEnabled());

        userRepository.save(user);
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    void updateUserPassword(@RequestBody UserJson userJson) {

        User user = userRepository.findOne(userJson.getId());
        user.setPassword(passwordEncoder.encode(userJson.getPassword()));

        userRepository.save(user);
    }
}