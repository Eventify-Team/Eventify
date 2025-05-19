package com.evetify.eventify.services;

import com.evetify.eventify.models.*;
import com.evetify.eventify.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EventService eventService;



    public void removeAttendance(Long attendanceId) {
        Optional<Attendance> att = attendanceRepository.findById(attendanceId);
        if(!att.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");

        attendanceRepository.deleteById(attendanceId);
    }

    public void addAttendance(Long userId, Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        User user = optionalUser.get();
        Attendance attendance = new Attendance(user, event);
        Reservation reservation = attendance.getReservation();
        reservationRepository.save(reservation);
        attendance.setReservation(reservation);
        attendanceRepository.save(attendance);
        eventService.addAttendance(eventId,attendance);

    }

    public List<Attendance> getAttForUser(Long userId) {
        return  attendanceRepository.findByUser(userId);
    }

    public boolean checkReservation(Long attendanceId){
        Optional<Attendance> att = attendanceRepository.findById(attendanceId);
        if(!att.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        Attendance attendance = att.get();
        return  attendance.getReservation().isValid();
    }

}
