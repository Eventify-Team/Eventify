package com.evetify.eventify.controllers;

import com.evetify.eventify.models.*;
import com.evetify.eventify.repositories.UserRepository;
import com.evetify.eventify.services.EventService;
import com.evetify.eventify.services.RatingService;
import com.evetify.eventify.services.ReservationService;
import com.evetify.eventify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    EventService eventService;

    @Autowired
    RatingService ratingService;

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

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        Reservation res = reservationService.addReservation(reservation);
        return res;
    }

    @DeleteMapping()
    public void removeResevation(@RequestParam Long id){
        reservationService.removeReservation(id);
    }

    @GetMapping("/getevent")
    public Event getEvent(@RequestParam Long id){
        Event event = eventService.getEvent(id);
        return event;
    }

    @GetMapping("/getallevents")
    public List<Event> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return events;
    }

    @PostMapping()
    public void addAttendance(@RequestParam Long userId, @RequestBody Attendance attendance){
        userService.addAttendance(userId, attendance);
    }

    @PostMapping()
    public void addRatingForEvent (@RequestParam Long userId, @RequestParam Long eventId, @RequestParam Integer score){
        userService.addRatingForEvent(userId, eventId, score);
    }

    @PostMapping()
    public void cancelReservation (@RequestParam Long userId, @RequestParam Long eventId){
        userService.cancelReservation(userId, eventId);
    }
}
