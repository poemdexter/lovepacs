package org.lovepacs.controllers;

import org.lovepacs.exception.UserNotFoundException;
import org.lovepacs.json.UserJson;
import org.lovepacs.models.User;
import org.lovepacs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    User getUser(@PathVariable("id") final String id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    User getWhoAmI(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return user;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    void createUser(@RequestBody UserJson userJson) {

        User user = new User();
        user.setName(userJson.getName());
        user.setPassword(passwordEncoder.encode(userJson.getPassword()));
        user.setEnabled(userJson.getEnabled());
        user.setLocation(userJson.getLocation());

        userRepository.save(user);

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    void updateUser(@RequestBody UserJson userJson) {

        User user = userRepository.findByName(userJson.getName());

        if(user == null) {
            throw new UserNotFoundException();
        }

        if (userJson.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userJson.getPassword()));
        }

        user.setEnabled(userJson.getEnabled());
        user.setLocation(userJson.getLocation());

        userRepository.save(user);
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such username")
    @ExceptionHandler(UsernameNotFoundException.class)
    void notFoundHandler() {

    }
}