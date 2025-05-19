package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Attendance;
import com.evetify.eventify.models.Event;
import com.evetify.eventify.repositories.EventRepository;
import com.evetify.eventify.services.AttendanceService;
import com.evetify.eventify.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    EventRepository eventRepository;

    @DeleteMapping
    public void deleteAttendance(@RequestParam Long attendanceId){
        attendanceService.removeAttendance(attendanceId);
    }

    @PostMapping
    public void addAttendance(@RequestParam Long userId, @RequestParam Long eventId){
        attendanceService.addAttendance(userId,eventId);
    }
    @GetMapping
    public List<Attendance> getAttendancesForUser(@RequestParam Long userId){
        return attendanceService.getAttForUser(userId);
    }
}
