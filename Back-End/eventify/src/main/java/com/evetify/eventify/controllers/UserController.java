package com.evetify.eventify.controllers;

import com.evetify.eventify.models.*;
import com.evetify.eventify.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    AttendanceService attendanceService;

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/removeUser")
    public void removeUser(@RequestParam Long userId){
        userService.RemoveUser(userId);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestParam Long userId, @RequestParam (required = false) String name,
                           @RequestParam (required = false) String surname,
                           @RequestParam (required = false) String email,
                           @RequestParam (required = false) String password){
        User user = userService.updateUser(userId, name, surname, email, password);
        return user;

    }

    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody Reservation reservation){
        Reservation res = reservationService.addReservation(reservation);
        return res;
    }

    @GetMapping("/getEvent")
    public Event getEvent(@RequestParam Long eventId){
        Event event = eventService.getEvent(eventId);
        return event;
    }

    @GetMapping("/getAllEvents")
    public List<Event> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return events;
    }

    @PostMapping("/addAttendance")
    public void addAttendance(@RequestParam Long userId, @RequestParam Long eventId){
        attendanceService.addAttendance(userId, eventId);
    }

    @GetMapping("/getAttendancesForUser")
    public List<Attendance> getAttendancesForUser(@RequestParam Long userId){
        return attendanceService.getAttendancesForUser(userId);
    }

    @PostMapping("/addRating")
    public void addRatingForEvent (@RequestParam Long userId, @RequestParam Long eventId, @RequestParam Integer score){
        userService.addRatingForEvent(userId, eventId, score);
    }

    @DeleteMapping("/deleteReservation")
    public void deleteReservation (@RequestParam Long attendanceId){
        attendanceService.removeAttendance(attendanceId);
    }

//    @DeleteMapping("/deleteReservation")
//    public void cancelReservation (@RequestParam Long userId, @RequestParam Long eventId){
//        userService.cancelReservation(userId, eventId);
//    }
//    @DeleteMapping("/removeReservation")
//    public void removeReservation(@RequestParam Long id){
//        reservationService.removeReservation(id);
//    }
//
//    @PostMapping("/addAttendance")
//    public void addAttendance(@RequestParam Long userId, @RequestBody Attendance attendance){
//        userService.addAttendance(userId, attendance);
//    }
}
