package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Attendance;
import com.evetify.eventify.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

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
