package com.evetify.eventify.controllers;

import com.evetify.eventify.models.*;
import com.evetify.eventify.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    RatingService ratingService;

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

    @PostMapping()
    public Event updateEvent (@RequestParam Long id, @RequestParam (required = false) String name,
                              @RequestParam (required = false) String description,
                              @RequestParam (required = false) Integer duration,
                              @RequestParam (required = false) String location,
                              @RequestParam (required = false) Integer capacity,
                              @RequestParam (required = false) Date date,
                              @RequestParam (required = false) Double fee){
        Event event = eventService.updateEvent(id, name, description, duration, location, capacity, date, fee);
        return event;
    }

    @DeleteMapping()
    public List<Event> deleteEvent(@RequestParam Long id){
        List<Event> events = eventService.removeEvent(id);
        return events;
    }


    @GetMapping("/getallevents")
    public List<Event> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return events;
    }

    @GetMapping("/getevent")
    public Event getEvent(@RequestParam Long id){
        Event event = eventService.getEvent(id);
        return event;
    }


    @GetMapping("geteventwithname")
    public List<Event> getEventWithName (@RequestParam String name){
        List<Event> events = eventService.getEventWithName(name);
        return events;
    }

    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam Long id){
        return reservationService.getReservation(id);
    }

    @GetMapping("/getAllReservations")
    public ArrayList<Reservation> getAllReservations(){
        ArrayList<Reservation> list = reservationService.getAllReservations();
        return list;
    }

    @GetMapping("/getAllReservationsForEvent")
    public ArrayList<Reservation> getAllReservationsForEvent(@RequestParam Long eventId){
        Event event = eventService.getEvent(eventId);
        ArrayList<Reservation> list = event.getAllReservationsForEvent();
        return list;
    }

    @GetMapping("/getAllRatingsForEvent")
    public ArrayList<Rating> getRatingsForEvent(@RequestParam Long eventId){
        Event event = eventService.getEvent(eventId);
        return event.getAllRatingsForEvent();
    }

    @GetMapping("/getUsersForEvent")
    public ArrayList<User> getUsersForEvent(@RequestParam Long eventId){
        return reservationService.getAllUsersForEvent(eventId);
    }


}
