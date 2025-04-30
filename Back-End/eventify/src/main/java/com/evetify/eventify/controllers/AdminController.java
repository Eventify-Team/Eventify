package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Admin;
import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.User;
import com.evetify.eventify.services.AdminService;
import com.evetify.eventify.services.EventService;
import com.evetify.eventify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @PostMapping
    public void addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
    }

    @PostMapping
    public void RemoveAdmin(@RequestParam Long id){
        adminService.RemoveAdmin(id);
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam Long id){
        return userService.getUser(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByUsername")
    public User getUserByUsername(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public Admin updateAdmin(@RequestParam Long id, @RequestParam (required = false) String name,
                           @RequestParam (required = false) String password,
                           @RequestParam (required = false) String email){
        Admin admin = adminService.updateAdmin(id, name, password,email);
        return admin;

    }


    @PostMapping
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }


}
