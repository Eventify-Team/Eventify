package com.evetify.eventify.controllers;

import com.evetify.eventify.models.*;
import com.evetify.eventify.repositories.UserRepository;
import com.evetify.eventify.services.*;
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

    @Autowired
    AttendanceService attendanceService;

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping
    public void removeUser(@RequestParam Long id){
        userService.RemoveUser(id);
    }

    @PutMapping
    public User updateUser(@RequestParam Long id, @RequestParam (required = false) String name,
                           @RequestParam (required = false) String surname,
                           @RequestParam (required = false) String username,
                           @RequestParam (required = false) String email,
                           @RequestParam (required = false) String password){
        User user = userService.updateUser(id, name, surname, username, email, password);
        return user;

    }

    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody Reservation reservation){
        Reservation res = reservationService.addReservation(reservation);
        return res;
    }

    @DeleteMapping("/removeReservation")
    public void removeResevation(@RequestParam Long id){
        reservationService.removeReservation(id);
    }

    @GetMapping("/getEvent")
    public Event getEvent(@RequestParam Long id){
        Event event = eventService.getEvent(id);
        return event;
    }

    @GetMapping("/getAllEvents")
    public List<Event> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return events;
    }
//
//    @PostMapping("/addAttendance")
//    public void addAttendance(@RequestParam Long userId, @RequestBody Attendance attendance){
//        userService.addAttendance(userId, attendance);
//    }

    @PostMapping("/addAttendance")
    public void addAttendance(@RequestParam Long userId, @RequestParam Long eventId){
        attendanceService.addAttendance(userId, eventId);
    }

    @GetMapping("/getAttendancesForUser")
    public List<Attendance> getAttendancesForUser(@RequestParam Long userId){
        return attendanceService.getAttForUser(userId);
    }

    @PostMapping("/addRating")
    public void addRatingForEvent (@RequestParam Long userId, @RequestParam Long eventId, @RequestParam Integer score){
        userService.addRatingForEvent(userId, eventId, score);
    }

    @DeleteMapping("/deleteReservation")
    public void cancelReservation (@RequestParam Long userId, @RequestParam Long eventId){
        userService.cancelReservation(userId, eventId);
    }
}
