package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.User;
import com.evetify.eventify.repositories.UserRepository;
import com.evetify.eventify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping()
    public void RemoveUser(@RequestParam Long id){
        userService.RemoveUser(id);
    }

    @PostMapping
    public User updateUser(@RequestParam Long id, @RequestParam (required = false) String name,
                           @RequestParam (required = false) String surname,
                           @RequestParam (required = false) String username,
                           @RequestParam (required = false) String email,
                           @RequestParam (required = false) String password){
        User user = userService.updateUser(id, name, surname, username, email, password);
        return user;

    }
}
