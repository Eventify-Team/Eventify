package com.evetify.eventify.controllers;

import com.evetify.eventify.models.*;
import com.evetify.eventify.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

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

    @DeleteMapping
    public void removeAdmin(@RequestParam Long id){
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

    @PutMapping
    public Admin updateAdmin(@RequestParam Long id, @RequestParam (required = false) String name,
                           @RequestParam (required = false) String password,
                           @RequestParam (required = false) String email){
        Admin admin = adminService.updateAdmin(id, name, password,email);
        return admin;

    }


    @PostMapping("/addEvent")
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }

    @PutMapping("/updateEvent")
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

    @DeleteMapping("/deleteEvent")
    public List<Event> deleteEvent(@RequestParam Long id){
        List<Event> events = eventService.removeEvent(id);
        return events;
    }


    @GetMapping("/getallevents")
    public List<Event> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return events;
    }

    @GetMapping("/getEvent")
    public Event getEvent(@RequestParam Long id){
        Event event = eventService.getEvent(id);
        return event;
    }


    @GetMapping("/getEventWithName")
    public List<Event> getEventWithName (@RequestParam String name){
        List<Event> events = eventService.getEventWithName(name);
        return events;
    }

    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam Long id){
        return reservationService.getReservation(id);
    }

    @GetMapping("/getAllReservations")
    public List<Reservation> getAllReservations(){
        List<Reservation> list = reservationService.getAllReservations();
        return list;
    }

    @GetMapping("/getAllReservationsForEvent")
    public List<Reservation> getAllReservationsForEvent(@RequestParam Long eventId){
        List<Reservation> reservations = reservationService.getAllReservationsForEvent(eventId);
        return reservations;
    }

    @GetMapping("/getAllRatingsForEvent")
    public HashMap<String, Integer> getRatingsForEvent(@RequestParam Long eventId){
        HashMap<String, Integer> ratings = eventService.getAllRatingsForEvent(eventId);
        return ratings;
    }

    @GetMapping("/getUsersForEvent")
    public List<User> getUsersForEvent(@RequestParam Long eventId){
        return reservationService.getAllUsersForEvent(eventId);
    }
}
