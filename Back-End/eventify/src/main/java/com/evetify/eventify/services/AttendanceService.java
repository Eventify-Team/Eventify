package com.evetify.eventify.services;

import com.evetify.eventify.models.Attendance;
import com.evetify.eventify.repositories.AttendanceRepository;
import com.evetify.eventify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    public void removeAttendance(Long attendanceId) {
        Optional<Attendance> att = attendanceRepository.findById(attendanceId);
        if(!att.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");

        attendanceRepository.deleteById(attendanceId);
    }

    public void addAttendance(Attendance att) {
        attendanceRepository.save(att);
    }

    public ArrayList<Attendance> getAttForUser(Long userId) {
        return  attendanceRepository.findByUser(userId);
    }
}
