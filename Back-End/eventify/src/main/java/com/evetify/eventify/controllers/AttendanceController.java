package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Attendance;
import com.evetify.eventify.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @DeleteMapping
    public void deleteAttendance(Long attendanceId){
        attendanceService.removeAttendance(attendanceId);
    }

    @PostMapping
    public void addAttendance(@RequestBody Attendance att){
        attendanceService.addAttendance(att);
    }
    @GetMapping
    public ArrayList<Attendance> getAttendancesForUser(Long userId){
        return attendanceService.getAttForUser(userId);
    }
}
