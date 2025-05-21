package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Attendance;
import com.evetify.eventify.repositories.EventRepository;
import com.evetify.eventify.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

//    @DeleteMapping("/deleteAttendance")
//    public void deleteAttendance(@RequestParam Long attendanceId){
//        attendanceService.removeAttendance(attendanceId);
//    }
//
//    @PostMapping("/addAttendance")
//    public void addAttendance(@RequestParam Long userId, @RequestParam Long eventId){
//        attendanceService.addAttendance(userId,eventId);
//    }
//    @GetMapping("getAttendancesForUser")
//    public List<Attendance> getAttendancesForUser(@RequestParam Long userId){
//        return attendanceService.getAttendancesForUser(userId);
//    }
}
